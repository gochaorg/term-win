package xyz.cofe.term.win;

import com.sun.jna.platform.win32.Kernel32Util;
import com.sun.jna.platform.win32.WinNT;

import java.util.List;
import java.util.Optional;

import static xyz.cofe.term.win.WinConsoleError.throwError;
import static xyz.cofe.term.win.impl.RawAPIHolder.rawAPI;

// https://docs.microsoft.com/en-us/windows/console/using-the-high-level-input-and-output-functions
public class WinConsole {
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

    private final WinNT.HANDLE outputHandle;
    private final WinConsoleOutput output;
    private final WinNT.HANDLE inputHandle;
    private final WinConsoleInput input;

    private final WinNT.HANDLE errorHandle;
    private final WinConsoleOutput errput;

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
        output = new WinConsoleOutput(outputHandle);

        inputHandle = getStdInputHandle();
        input = new WinConsoleInput(inputHandle);

        errorHandle = getStdErrorHandle();
        errput = new WinConsoleOutput(errorHandle);
    }

    public WinConsoleOutput getOutput(){ return output; }
    public WinConsoleOutput getErrput(){ return errput; }
    public WinConsoleInput getInput(){ return input; }

    //region availableInputEventsCount : int
    public int getAvailableInputEventsCount() {
        return input.getAvailableInputEventsCount();
    }
    //endregion
    //region inputMode : InputMode
    public InputMode getInputMode() {
        return input.getInputMode();
    }

    public void setInputMode(InputMode mode) {
        input.setInputMode(mode);
    }
    //endregion
    //region inputCodePage : CodePage
    public Optional<CodePage> getInputCodePageOptional() {
        return input.getCodePageOptional();
    }

    public void setInputCodePage(CodePage codePage) {
        input.setCodePage(codePage);
    }
    //endregion
    //region read(eventCount):List<InputEvent>
    public List<InputEvent> read(int eventCount) {
        return input.read(eventCount);
    }
    //endregion
    //region read():List<InputEvent>
    public List<InputEvent> read() {
        return input.read();
    }
    //endregion
    //region peek(eventCount):List<InputEvent>
    public List<InputEvent> peek(int eventCount) {
        return input.peek(eventCount);
    }
    //endregion
    //region flush()
    public void flushInput() {
        input.flush();
    }
    //endregion

    //region screenBufferMode : ScreenBufferMode
    public ScreenBufferMode getScreenBufferMode() {
        return output.getScreenBufferMode();
    }

    public void setScreenBufferMode(ScreenBufferMode mode) {
        output.setScreenBufferMode(mode);
    }
    //endregion
    //region outputCodePage : CodePage
    public Optional<CodePage> getOutputCodePageOptional() {
        return output.getCodePageOptional();
    }

    public void setOutputCodePage(CodePage codePage) {
        output.setCodePage(codePage);
    }
    //endregion
    //region cursorInfo : CursorInfo
    public CursorInfo getCursorInfo() {
        return output.getCursorInfo();
    }

    public void setCursorInfo(CursorInfo cursorInfo) {
        output.setCursorInfo(cursorInfo);
    }
    //endregion

    //region screenBufferInfo : ScreenBufferInfo
    public ScreenBufferInfo getScreenBufferInfo() {
        return output.getScreenBufferInfo();
    }
    //endregion
    //region charAttributes : CharAttributes
    public CharAttributes getCharAttributes() {
        return output.getCharAttributes();
    }

    public void setCharAttributes(CharAttributes attributes) {
        if( attributes==null )throw new IllegalArgumentException("attributes==null");
        output.setCharAttributes(attributes);
    }
    //endregion

    public int write(String text) {
        if( text==null )throw new IllegalArgumentException("text==null");
        return output.write(text);
    }

    public void cursor(int x, int y) {
        output.cursor(x, y);
    }

    //region largestSize : LargestSize
    public LargestSize getLargestSize() {
        return output.getLargestSize();
    }
    //endregion
    //region title : String
    public String getTitle(){
        return WinConsoleCommon.getTitle();
    }
    public void setTitle(String title){
        if( title==null )throw new IllegalArgumentException("title==null");
        WinConsoleCommon.setTitle(title);
    }
    //endregion

    public ControlHolder controlHandle(ControlHandler handler){
        if( handler==null )throw new IllegalArgumentException("handler==null");
        return WinConsoleCommon.handleControl(handler);
    }

    public void controlHandleReset(){
        WinConsoleCommon.restoreControl();
    }
}
