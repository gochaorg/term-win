package xyz.cofe.term.win;

import com.sun.jna.platform.win32.Wincon;

public class InputKeyEvent implements InputEvent {
    private final boolean keyDown;
    private final int repeatCount;
    private final int keyCode;
    private final int scanCode;
    private final char character;
    private final int state;

    public static enum StateFlags implements BitFlag {
        CAPSLOCK_ON(0x80),
        ENHANCED_KEY(0x100),
        LEFT_ALT_PRESSED(0x02),
        LEFT_CTRL_PRESSED(0x0008),
        NUMLOCK_ON(0x0020),
        RIGHT_ALT_PRESSED (0x0001),
        RIGHT_CTRL_PRESSED (0x0004),
        SCROLLLOCK_ON (0x0040),
        SHIFT_PRESSED (0x0010),
        ;

        private final int flag;
        StateFlags(int flag){
            this.flag = flag;
        }

        @Override
        public int bitFlag() {
            return flag;
        }
    }

    public InputKeyEvent(Wincon.KEY_EVENT_RECORD keyEventRecord){
        if( keyEventRecord==null )throw new IllegalArgumentException("keyEventRecord==null");
        keyDown = keyEventRecord.bKeyDown;
        repeatCount = keyEventRecord.wRepeatCount;
        keyCode = keyEventRecord.wVirtualKeyCode;
        scanCode = keyEventRecord.wVirtualScanCode;
        character = keyEventRecord.uChar;
        state = keyEventRecord.dwControlKeyState;
    }

    public boolean isCapsLock(){ return StateFlags.CAPSLOCK_ON.has(state); }
    public boolean isEnhanced(){ return StateFlags.ENHANCED_KEY.has(state); }
    public boolean isLeftAlt(){ return StateFlags.LEFT_ALT_PRESSED.has(state); }
    public boolean isLeftCtrl(){ return StateFlags.LEFT_CTRL_PRESSED.has(state); }
    public boolean isNumLock(){ return StateFlags.NUMLOCK_ON.has(state); }
    public boolean isRightAlt(){ return StateFlags.RIGHT_ALT_PRESSED.has(state); }
    public boolean isRightCtrl(){ return StateFlags.RIGHT_CTRL_PRESSED.has(state); }
    public boolean isScrollLock(){ return StateFlags.SCROLLLOCK_ON.has(state); }
    public boolean isShift(){ return StateFlags.SHIFT_PRESSED.has(state); }

    public boolean isKeyDown() {
        return keyDown;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getScanCode() {
        return scanCode;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {

        return "InputKeyEvent{" +
                "keyDown=" + keyDown +
                ", repeatCount=" + repeatCount +
                ", keyCode=" + keyCode +
                ", scanCode=" + scanCode +
                ", character=" + character +
                ", state={"+
                (isCapsLock() ? "Caps" : "")+
                (isEnhanced() ? "Enhanced" : "")+
                (isLeftAlt() ? "Alt(left)" : "")+
                (isLeftCtrl() ? "Ctrl(left)" : "")+
                (isNumLock() ? "Num" : "")+
                (isRightAlt() ? "Alt(right)" : "")+
                (isRightCtrl() ? "Ctrl(right)" : "")+
                (isScrollLock() ? "Scroll" : "")+
                (isShift() ? "Shift" : "")+
                "}"+
                '}';
    }
}
