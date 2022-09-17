package xyz.cofe.term.win;

import java.util.Arrays;

import static xyz.cofe.term.win.WinConsoleError.throwError;
import static xyz.cofe.term.win.impl.RawAPIHolder.rawAPI;

public class ControlHolder implements WinConsoleRawAPI.ConsoleCtrlHandler, AutoCloseable {
    public final ControlHandler handler;
    private volatile boolean closed = false;

    public ControlHolder(ControlHandler handler) {
        if (handler == null) throw new IllegalArgumentException("handler==null");
        this.handler = handler;
    }

    @Override
    public boolean handle(int fdwCtrlType) {
        var ev = Arrays.stream(ControlEvent.values()).filter(c -> c.code == fdwCtrlType && c != ControlEvent.Unknown).findFirst();
        return handler.controlEvent(ev.orElseGet(() -> ControlEvent.Unknown));
    }

    public synchronized void close() {
        if (closed) return;
        closed = true;
        if (!rawAPI().SetConsoleCtrlHandler(this, false)) {
            throwError("SetConsoleCtrlHandler(this, false)");
        }
    }
}
