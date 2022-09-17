package xyz.cofe.term.win;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;

import static xyz.cofe.term.win.WinConsoleError.throwError;
import static xyz.cofe.term.win.impl.RawAPIHolder.rawAPI;

public class WinConsoleCommon {
    /**
     * Retrieves the title for the current console window.
     * @return title
     */
    public static String getTitle(){
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
    public static void setTitle(String title){
        if( title==null )throw new IllegalArgumentException("title==null");
        if( !rawAPI().SetConsoleTitleW(new WString(title)) ){
            throwError("SetConsoleTitleW");
        }
    }

    private static final int CONSOLE_TEXTMODE_BUFFER = 1;
    private static final WinDef.LPVOID nullRef = new WinDef.LPVOID();
    public static WinConsoleOutput createOutput(){
        var dwDesiredAccess = WinNT.GENERIC_READ | WinNT.GENERIC_WRITE;
        var dwShareMode = WinNT.FILE_SHARE_READ | WinNT.FILE_SHARE_WRITE;
        var handle = rawAPI().CreateConsoleScreenBuffer(
            dwDesiredAccess,
            dwShareMode,
            nullRef,
            CONSOLE_TEXTMODE_BUFFER,
            nullRef
        );
        if( WinConsoleRawAPI.INVALID_HANDLE_VALUE.equals(handle) ){
            throwError("CreateConsoleScreenBuffer("+dwDesiredAccess+","+dwShareMode+",nullRef,"+CONSOLE_TEXTMODE_BUFFER+",nullRef)");
        }
        return new WinConsoleOutput(handle,true);
    }

    public static void restoreControl(){
        if( !rawAPI().SetConsoleCtrlHandler(null,false) ){
            throwError("SetConsoleCtrlHandler(null,false)");
        }
    }

    // https://learn.microsoft.com/en-us/windows/console/setconsolectrlhandler
    public static ControlHolder handleControl(ControlHandler handler){
        if( handler==null )throw new IllegalArgumentException("handler==null");
        ControlHolder holder = new ControlHolder(handler);
        if( !rawAPI().SetConsoleCtrlHandler(holder,true) ){
            throwError("SetConsoleCtrlHandler(holder,true)");
        }
        return holder;
    }
}
