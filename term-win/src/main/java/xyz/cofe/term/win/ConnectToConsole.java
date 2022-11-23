package xyz.cofe.term.win;

import static com.sun.jna.platform.win32.WinError.ERROR_ACCESS_DENIED;
import static com.sun.jna.platform.win32.WinError.ERROR_INVALID_PARAMETER;
import static com.sun.jna.platform.win32.Wincon.ATTACH_PARENT_PROCESS;
import static xyz.cofe.term.win.WinConsoleError.throwError;
import static xyz.cofe.term.win.impl.RawAPIHolder.rawAPI;

/**
 * Указывает как присоедениться или открыть консоль
 */
public interface ConnectToConsole {
    /**
     * Присоедениться к консоли
     */
    void connect() throws WinConsoleError;

    /**
     * Открывает новую консоль
     */
    public static class AllocConsole implements ConnectToConsole {
        @Override
        public void connect() throws WinConsoleError {
            if (!rawAPI().AllocConsole()) {
                throwError("AllocConsole");
            }
        }
    }

    /**
     * Присоедениться к родительской
     */
    public static class AttachParent implements ConnectToConsole {
        @Override
        public void connect() throws WinConsoleError {
            if (!rawAPI().AttachConsole(ATTACH_PARENT_PROCESS)) {
                throwError("AttachConsole");
            }
        }
    }

    /**
     * Сначала попытка присоединиться к родительской,
     * если не получилось, тогда открыть новую
     */
    public static class TryAttachParent implements ConnectToConsole {
        @Override
        public void connect() throws WinConsoleError {
            System.out.println("TryAttachParent");

            if( rawAPI().AttachConsole(ATTACH_PARENT_PROCESS) ) {
                System.out.println("AttachConsole ATTACH_PARENT_PROCESS success");
                return;
            }
            var attachError = rawAPI().GetLastError();
            // from https://learn.microsoft.com/en-us/windows/console/attachconsole
            //   If the calling process is already attached to a console, the error code returned is ERROR_ACCESS_DENIED
            //   If the specified process does not have a console, the error code returned is ERROR_INVALID_HANDLE
            //   If the specified process does not exist, the error code returned is ERROR_INVALID_PARAMETER

            if( attachError==ERROR_ACCESS_DENIED ) {
                System.out.println("AttachConsole ATTACH_PARENT_PROCESS - already attached");
                return;
            }

            System.out.println("AttachConsole ATTACH_PARENT_PROCESS - fail");

            if (!rawAPI().AllocConsole()) {
                throwError("AllocConsole");
            }else{
                System.out.println("console alloc succ");
            }
        }
    }
}
