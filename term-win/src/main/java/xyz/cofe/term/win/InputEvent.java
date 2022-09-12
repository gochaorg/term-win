package xyz.cofe.term.win;

import com.sun.jna.platform.win32.Wincon;

import java.util.Optional;

public interface InputEvent {
    public static Optional<InputEvent> read(Wincon.INPUT_RECORD inputRecord){
        if( inputRecord==null )throw new IllegalArgumentException("inputRecord==null");
        inputRecord.read();
        switch (inputRecord.EventType){
            case Wincon.INPUT_RECORD.KEY_EVENT:
                return Optional.of(new InputKeyEvent(inputRecord.Event.KeyEvent));
            case Wincon.INPUT_RECORD.MOUSE_EVENT:
                return Optional.of(new InputMouseEvent(inputRecord.Event.MouseEvent));
            case Wincon.INPUT_RECORD.WINDOW_BUFFER_SIZE_EVENT:
                return Optional.of(new InputWindowEvent(inputRecord.Event.WindowBufferSizeEvent));
            default:
                return Optional.empty();
        }
    }
}
