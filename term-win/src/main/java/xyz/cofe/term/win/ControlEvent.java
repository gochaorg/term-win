package xyz.cofe.term.win;

// https://learn.microsoft.com/en-us/windows/console/handlerroutine
public enum ControlEvent {
    /** Control+C */
    CtrlC(0),

    /** Control+Break */
    CtrlBreak(1),

    /**
     * either by clicking Close on the console window's window menu, or by clicking the End Task button command from Task Manager
     */
    Close(2),

    /**
     * A signal that the system sends to all console processes when a user is logging off. This signal does not indicate which user is logging off, so no assumptions can be made.
     *
     * Note that this signal is received only by services. Interactive applications are terminated at logoff, so they are not present when the system sends this signal.
     */
    Logoff(5),

    /**
     * A signal that the system sends when the system is shutting down. Interactive applications are not present by the time the system sends this signal, therefore it can be received only be services in this situation.
     */
    Shutdown(6),

    Unknown(-1)
    ;

    public final int code;

    ControlEvent(int code){
        this.code = code;
    }
}
