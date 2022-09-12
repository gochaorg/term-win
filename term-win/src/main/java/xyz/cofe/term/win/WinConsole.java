package xyz.cofe.term.win;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32Util;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.Wincon;
import com.sun.jna.ptr.IntByReference;

import java.util.ArrayList;
import java.util.List;

import static xyz.cofe.term.win.WinConsoleError.throwError;

// https://docs.microsoft.com/en-us/windows/console/using-the-high-level-input-and-output-functions
public class WinConsole {
    private static volatile WinConsoleRawAPI api;
    public static WinConsoleRawAPI rawAPI(){
        if( api!=null )return api;
        synchronized (WinConsole.class){
            if( api!=null )return api;
            api = Native.load("Kernel32", WinConsoleRawAPI.class);
            return api;
        }
    }

    //region stdOutputHandle, stdInputHandle, stdErrorHandle
    private static volatile WinNT.HANDLE stdOutputHandle;
    public static WinNT.HANDLE getStdOutputHandle(){
        if( stdOutputHandle !=null )return stdOutputHandle;
        synchronized (WinConsole.class){
            if( stdOutputHandle !=null )return stdOutputHandle;
            var handle = rawAPI().GetStdHandle(WinConsoleRawAPI.STD_OUTPUT_HANDLE);
            if( WinConsoleRawAPI.INVALID_HANDLE_VALUE.equals(handle) ){
                throwError("GetStdHandle(STD_OUTPUT_HANDLE) return INVALID_HANDLE_VALUE");
            }
            stdOutputHandle = handle;
            return stdOutputHandle;
        }
    }

    private static volatile WinNT.HANDLE stdInputHandle;
    public static WinNT.HANDLE getStdInputHandle(){
        if( stdInputHandle!=null )return stdInputHandle;
        synchronized (WinConsole.class){
            if( stdInputHandle!=null )return stdInputHandle;
            var handle = rawAPI().GetStdHandle(WinConsoleRawAPI.STD_INPUT_HANDLE);
            if( WinConsoleRawAPI.INVALID_HANDLE_VALUE.equals(handle) ){
                throwError("GetStdHandle(STD_INPUT_HANDLE) return INVALID_HANDLE_VALUE");
            }
            stdInputHandle = handle;
            return stdInputHandle;
        }
    }

    private static volatile WinNT.HANDLE stdErrorHandle;
    public static WinNT.HANDLE getStdErrorHandle(){
        if( stdErrorHandle!=null )return stdErrorHandle;
        synchronized (WinConsole.class){
            if( stdErrorHandle!=null )return stdErrorHandle;
            var handle = rawAPI().GetStdHandle(WinConsoleRawAPI.STD_ERROR_HANDLE);
            if( WinConsoleRawAPI.INVALID_HANDLE_VALUE.equals(handle) ){
                throwError("GetStdHandle(STD_ERROR_HANDLE) return INVALID_HANDLE_VALUE");
            }
            stdErrorHandle = handle;
            return stdErrorHandle;
        }
    }
    //endregion

    private static volatile boolean allocated;

    private WinNT.HANDLE outputHandle;
    private WinNT.HANDLE inputHandle;
    private WinNT.HANDLE errorHandle;

    public WinConsole(){
        synchronized (WinConsole.class){
            if( !allocated ){
                if( !rawAPI().AllocConsole() ){
                    throwError("AllocConsole");
                }
                allocated = true;
                Runtime.getRuntime().addShutdownHook(new Thread(()->{
                    if( !rawAPI().FreeConsole() ){
                        var errno = rawAPI().GetLastError();
                        var errmsg = Kernel32Util.getLastErrorMessage();
                        System.err.println("FreeConsole fail: "+errno+" "+errmsg);
                    }
                }));
            }
        }
        outputHandle = getStdOutputHandle();
        inputHandle = getStdInputHandle();
        errorHandle = getStdErrorHandle();
    }

    //region title : String
    /**
     * Retrieves the title for the current console window.
     * @return title
     */
    public synchronized String getTitle(){
        char[] buff = new char[1024*64];
        var charsCount = rawAPI().GetConsoleTitleW(buff,buff.length);
        if( charsCount==0 ){
            throwError("GetConsoleTitleW");
        }
        return charsCount<0 ? "" : new String(buff, 0, charsCount);
    }

    /**
     * Sets the title for the current console window.
     * @param title title
     */
    public synchronized void setTitle(String title){
        if( title==null )throw new IllegalArgumentException("title==null");
        if( !rawAPI().SetConsoleTitleW(title) ){
            throwError("SetConsoleTitleW");
        }
    }
    //endregion
    //region consoleMode : ConsoleMode
    public synchronized ConsoleMode getConsoleMode(){
        IntByReference outputMode = new IntByReference();
        if( !rawAPI().GetConsoleMode(outputHandle,outputMode) ){
            throwError("GetConsoleMode(outputHandle)");
        }

        IntByReference errorMode = new IntByReference();
        if( !rawAPI().GetConsoleMode(errorHandle,outputMode) ){
            throwError("GetConsoleMode(errorHandle)");
        }

        IntByReference inputMode = new IntByReference();
        if( !rawAPI().GetConsoleMode(inputHandle,inputMode) ){
            throwError("GetConsoleMode(inputHandle)");
        }

        return new ConsoleMode(outputMode.getValue(), errorMode.getValue(), inputMode.getValue());
    }

    public synchronized void setConsoleMode(ConsoleMode mode, boolean output, boolean error, boolean input){
        if( mode==null )throw new IllegalArgumentException("mode==null");

        if( output ) {
            if (!rawAPI().SetConsoleMode(outputHandle, mode.getOutputFlags())) {
                throwError("SetConsoleMode(outputHandle," + mode.getOutputFlags() + ")");
            }
        }

        if( error ) {
            if (!rawAPI().SetConsoleMode(errorHandle, mode.getErrorFlags())) {
                throwError("SetConsoleMode(errorHandle," + mode.getOutputFlags() + ")");
            }
        }

        if( input ) {
            if (!rawAPI().SetConsoleMode(inputHandle, mode.getInputFlags())) {
                throwError("SetConsoleMode(inputHandle," + mode.getInputFlags() + ")");
            }
        }
    }

    public synchronized void setConsoleMode(ConsoleMode mode){
        if( mode==null )throw new IllegalArgumentException("mode==null");
        setConsoleMode(mode, true, true, true);
    }
    //endregion

    public synchronized ScreenBufferInfo getScreenBufferInfo(){
        var info = new Wincon.CONSOLE_SCREEN_BUFFER_INFO();
        if( !rawAPI().GetConsoleScreenBufferInfo(outputHandle,info) ){
            throwError("GetConsoleScreenBufferInfo(outputHandle)");
        }
        return new ScreenBufferInfo(info);
    }

    public synchronized int availableInputEventsCount(){
        var cnt = new IntByReference();
        if( !rawAPI().GetNumberOfConsoleInputEvents(inputHandle,cnt) ){
            throwError("GetNumberOfConsoleInputEvents(inputHandle,cnt)");
        }
        return cnt.getValue();
    }

    public synchronized List<InputEvent> readInput(int eventCount){
        if( eventCount<0 )throw new IllegalArgumentException("eventCount<0");
        if( eventCount==0 )return List.of();

        var inputRecords = new Wincon.INPUT_RECORD[eventCount];
        var readed = new IntByReference();
        if( !rawAPI().ReadConsoleInputW(inputHandle,inputRecords,inputRecords.length,readed) ){
            throwError("ReadConsoleInput(inputHandle,inputRecords,inputRecords.length,readed)");
        }

        var cnt = readed.getValue();
        if( cnt<=0 )return List.of();

        var events = new ArrayList<InputEvent>();
        for( var i=0; i<cnt; i++ ){
            InputEvent.read(inputRecords[i]).ifPresent(events::add);
        }
        return events;
    }

    public synchronized List<InputEvent> readInput(){
        return readInput(availableInputEventsCount());
    }
}
