package xyz.cofe.term.win;

import com.sun.jna.platform.win32.Kernel32Util;

import static xyz.cofe.term.win.impl.RawAPIHolder.rawAPI;

public class WinConsoleError extends Error {
    public WinConsoleError() {
    }

    public WinConsoleError(String message) {
        super(message);
    }

    public WinConsoleError(String message, Throwable cause) {
        super(message, cause);
    }

    public WinConsoleError(Throwable cause) {
        super(cause);
    }

    public static void throwError(String whatHappened){
        if( whatHappened==null )throw new IllegalArgumentException("whatHappened==null");
        var errNo = rawAPI().GetLastError();
        var errMsg = Kernel32Util.getLastErrorMessage();
        throw new WinConsoleError(whatHappened+" code:"+errNo+" msg:"+errMsg);
    }
}
