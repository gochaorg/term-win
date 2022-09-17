package xyz.cofe.term.win;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.Wincon;
import com.sun.jna.ptr.IntByReference;

import java.io.Closeable;
import java.lang.ref.Cleaner;
import java.util.Arrays;
import java.util.Optional;

import static xyz.cofe.term.win.WinConsoleError.throwError;
import static xyz.cofe.term.win.impl.RawAPIHolder.rawAPI;

public class WinConsoleOutput implements AutoCloseable {
    private final WinNT.HANDLE outputHandle;
    private final boolean closeHandle;
    private volatile boolean released = false;

    public WinConsoleOutput(WinNT.HANDLE outputHandle){
        if( outputHandle==null )throw new IllegalArgumentException("outputHandle==null");
        this.outputHandle = outputHandle;
        this.closeHandle = false;
    }

    public WinConsoleOutput(WinNT.HANDLE outputHandle, boolean closeHandle){
        if( outputHandle==null )throw new IllegalArgumentException("outputHandle==null");
        this.outputHandle = outputHandle;
        this.closeHandle = closeHandle;
        if( closeHandle ) {
            var cleaner = Cleaner.create();
            cleaner.register(this, ()->{closeHandle(outputHandle);});
        }
    }

    private static void closeHandle(WinNT.HANDLE handle){
        rawAPI().CloseHandle(handle);
    }

    @Override
    public synchronized void close() throws Exception {
        var curState = released;
        released = true;
        if( !curState ){
            closeHandle(outputHandle);
        }
    }

    public WinNT.HANDLE getHandle(){ return outputHandle; }

    //region screenBufferMode
    public ScreenBufferMode getScreenBufferMode(){
        if( released )throw new IllegalStateException("closed");
        IntByReference outputMode = new IntByReference();
        if( !rawAPI().GetConsoleMode(outputHandle,outputMode) ){
            throwError("GetConsoleMode(outputHandle)");
        }

        return new ScreenBufferMode(outputMode.getValue());
    }

    public void setScreenBufferMode(ScreenBufferMode mode){
        if( mode==null )throw new IllegalArgumentException("mode==null");
        if( released )throw new IllegalStateException("closed");

        if (!rawAPI().SetConsoleMode(outputHandle, mode.getScreenBufferFlags())) {
            throwError("SetConsoleMode(outputHandle," + mode.getScreenBufferFlags() + ")");
        }
    }
    //endregion
    //region codePage
    public Optional<CodePage> getCodePageOptional(){
        if( released )throw new IllegalStateException("closed");
        int code = rawAPI().GetConsoleOutputCP();
        if( code==0 ){
            throwError("GetConsoleOutputCP");
        }
        return Arrays.stream(CodePage.values()).sequential().filter(cp -> cp.code == code).findFirst();
    }

    public void setCodePage(CodePage codePage){
        if( codePage==null )throw new IllegalArgumentException("codePage==null");
        if( released )throw new IllegalStateException("closed");
        if( !rawAPI().SetConsoleOutputCP(codePage.code) ){
            throwError("SetConsoleOutputCP("+codePage.code+")");
        }
    }
    //endregion
    //region cursorInfo
    public CursorInfo getCursorInfo(){
        if( released )throw new IllegalStateException("closed");
        var info = new WinConsoleRawAPI.CURSOR_INFO();
        if( !rawAPI().GetConsoleCursorInfo(outputHandle,info) ){
            throwError("GetConsoleCursorInfo(outputHandle,info)");
        }

        return new CursorInfo( info.size, info.visible );
    }

    public void setCursorInfo( CursorInfo cursorInfo ){
        if( cursorInfo==null )throw new IllegalArgumentException("cursorInfo==null");
        if( released )throw new IllegalStateException("closed");

        var info = new WinConsoleRawAPI.CURSOR_INFO();
        info.size = cursorInfo.getSize();
        info.visible = cursorInfo.isVisible();
        if( !rawAPI().SetConsoleCursorInfo(outputHandle, info) ){
            throwError("SetConsoleCursorInfo(outputHandle, info)");
        }
    }
    //endregion
    //region screenBufferInfo
    public ScreenBufferInfo getScreenBufferInfo(){
        if( released )throw new IllegalStateException("closed");
        var info = new Wincon.CONSOLE_SCREEN_BUFFER_INFO();
        if( !rawAPI().GetConsoleScreenBufferInfo(outputHandle,info) ){
            throwError("GetConsoleScreenBufferInfo(outputHandle)");
        }
        return new ScreenBufferInfo(info);
    }
    //endregion
    //region charAttributes
    public CharAttributes getCharAttributes(){
        return getScreenBufferInfo().getCharAttributes();
    }
    public void setCharAttributes(CharAttributes attributes){
        if( attributes==null )throw new IllegalArgumentException("attributes==null");
        if( released )throw new IllegalStateException("closed");
        if( !rawAPI().SetConsoleTextAttribute(outputHandle, (short) attributes.getFlags()) ){
            throwError("SetConsoleTextAttribute("+attributes.getFlags()+")");
        }
    }
    //endregion

    public int write(String text){
        if( text==null )throw new IllegalArgumentException("text==null");
        if( text.length()<1 )return 0;
        if( released )throw new IllegalStateException("closed");

        var written = new IntByReference();

        var wtext = new WString(text);
        if( !rawAPI().WriteConsoleW(outputHandle, new WString(text), text.length(), written, new WinDef.LPVOID()) ){
            throwError("WriteConsoleW("+text+","+text.length()+",written,LPVOID)");
        }

        return written.getValue();
    }
    public void cursor(int x, int y){
        if( released )throw new IllegalStateException("closed");

        var pos = new WinConsoleRawAPI.COORDbyValue();
        pos.X = (short)x;
        pos.Y = (short)y;
        if( !rawAPI().SetConsoleCursorPosition(outputHandle,pos) ){
            throwError("SetConsoleCursorPosition(outputHandle,pos)");
        }
    }
    public void activate(){
        if( released )throw new IllegalStateException("closed");

        if( !rawAPI().SetConsoleActiveScreenBuffer(outputHandle) ){
            throwError("SetConsoleActiveScreenBuffer(outputHandle)");
        }
    }

    public LargestSize getLargestSize(){
        if( released )throw new IllegalStateException("closed");

        var size = rawAPI().GetLargestConsoleWindowSize(outputHandle);
        if( size.X==0 || size.Y==0 ){
            throwError("GetLargestConsoleWindowSize(outputHandle)");
        }

        return new LargestSize(size.X, size.Y);
    }

    public void bufferSize(int width,int height){
        if( released )throw new IllegalStateException("closed");
        if( width<0 )throw new IllegalArgumentException("width<0");
        if( height<0 )throw new IllegalArgumentException("height<0");
        if( width>Short.MAX_VALUE )throw new IllegalArgumentException("width>Short.MAX_VALUE");
        if( height>Short.MAX_VALUE )throw new IllegalArgumentException("height>Short.MAX_VALUE");

        var size = new WinConsoleRawAPI.COORDbyValue();
        size.X = (short)width;
        size.Y = (short)height;

        if( !rawAPI().SetConsoleScreenBufferSize(outputHandle, size) ){
            throwError("SetConsoleScreenBufferSize(outputHandle, size)");
        }
    }

    public void windowRect(int left, int top, int right, int bottom){
        if( released )throw new IllegalStateException("closed");
        if( left<0 )throw new IllegalArgumentException("left<0");
        if( top<0 )throw new IllegalArgumentException("top<0");
        if( right<left )throw new IllegalArgumentException("right<left");
        if( bottom<top )throw new IllegalArgumentException("bottom<top");
        if( right>Short.MAX_VALUE )throw new IllegalArgumentException("right>Short.MAX_VALUE");
        if( bottom>Short.MAX_VALUE )throw new IllegalArgumentException("bottom>Short.MAX_VALUE");

        Wincon.SMALL_RECT rect = new Wincon.SMALL_RECT();
        rect.Left = (short) left;
        rect.Top = (short) top;
        rect.Right = (short) right;
        rect.Bottom = (short) bottom;

        if( !rawAPI().SetConsoleWindowInfo(outputHandle, true, rect) ){
            throwError("SetConsoleWindowInfo(outputHandle, true, rect)");
        }
    }
}
