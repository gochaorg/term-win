package xyz.cofe.term.win;

import com.sun.jna.platform.win32.Wincon;

import java.util.ArrayList;

public class InputMouseEvent implements InputEvent {
    //region buttons
    public static enum ButtonMask implements BitFlag {
        FROM_LEFT_1ST_BUTTON_PRESSED(0x01),
        FROM_LEFT_2ND_BUTTON_PRESSED(0x04),
        FROM_LEFT_3RD_BUTTON_PRESSED(0x08),
        FROM_LEFT_4TH_BUTTON_PRESSED(0x10),
        RIGHTMOST_BUTTON_PRESSED(0x02)
        ;

        ButtonMask(int flags){
            this.flags = flags;
        }


        private final int flags;
        @Override
        public int bitFlag() {
            return flags;
        }
    }

    private final int buttonState;

    private final boolean wheelEvent;
    public boolean isLeftButton(){ return !wheelEvent && ButtonMask.FROM_LEFT_1ST_BUTTON_PRESSED.has(buttonState); }
    public boolean isSecondButton(){ return !wheelEvent && ButtonMask.FROM_LEFT_2ND_BUTTON_PRESSED.has(buttonState); }
    public boolean isThirdButton(){ return !wheelEvent && ButtonMask.FROM_LEFT_2ND_BUTTON_PRESSED.has(buttonState); }
    public boolean isFourthButton(){ return !wheelEvent && ButtonMask.FROM_LEFT_4TH_BUTTON_PRESSED.has(buttonState); }
    public boolean isRightButton(){ return !wheelEvent && ButtonMask.RIGHTMOST_BUTTON_PRESSED.has(buttonState); }
    //endregion
    //region controlState
    private final int controlState;
    public static enum ControlState implements BitFlag {
        CAPSLOCK_ON (0x0080),
        ENHANCED_KEY(0x0100),
        LEFT_ALT_PRESSED (0x0002),
        LEFT_CTRL_PRESSED (0x0008),
        NUMLOCK_ON(0x0020),
        RIGHT_ALT_PRESSED (0x0001),
        RIGHT_CTRL_PRESSED (0x0004),
        SCROLLLOCK_ON (0x0040),
        SHIFT_PRESSED (0x0010)
        ;

        private final int flags;
        ControlState(int flags){
            this.flags = flags;
        }

        @Override
        public int bitFlag() {
            return flags;
        }
    }

    public boolean isCapsLock(){ return ControlState.CAPSLOCK_ON.has(controlState); }
    public boolean isEnhanced(){ return ControlState.ENHANCED_KEY.has(controlState); }
    public boolean isLeftAlt(){ return ControlState.LEFT_ALT_PRESSED.has(controlState); }
    public boolean isLeftCtrl(){ return ControlState.LEFT_CTRL_PRESSED.has(controlState); }
    public boolean isNumLock(){ return ControlState.NUMLOCK_ON.has(controlState); }
    public boolean isRightAlt(){ return ControlState.RIGHT_ALT_PRESSED.has(controlState); }
    public boolean isRightCtrl(){ return ControlState.RIGHT_CTRL_PRESSED.has(controlState); }
    public boolean isScrollLock(){ return ControlState.SCROLLLOCK_ON.has(controlState); }
    public boolean isShift(){ return ControlState.SHIFT_PRESSED.has(controlState); }
    //endregion
    //region x, y
    /**
     * location of the cursor, in terms of the console screen buffer's character-cell coordinates.
     */
    private final int x;

    /** location of the cursor, in terms of the console screen buffer's character-cell coordinates. */
    private final int y;


    public int getX(){ return x; }
    public int getY(){ return y; }
    //endregion
    //region event flags
    public static enum EventFlags implements BitFlag {
        DOUBLE_CLICK (0x0002),
        MOUSE_HWHEELED(0x0008),
        MOUSE_MOVED (0x0001),
        MOUSE_WHEELED (0x0004)
        ;
        private final int flags;
        EventFlags(int flags){
            this.flags = flags;
        }

        @Override
        public int bitFlag() {
            return flags;
        }
    }
    private final int eventFlags;
    public boolean isDoubleClick(){ return EventFlags.DOUBLE_CLICK.has(eventFlags); }
    public boolean isVerticalMouseWheel(){ return EventFlags.MOUSE_WHEELED.has(eventFlags); }
    public boolean isHorizontalMouseWheel(){ return EventFlags.MOUSE_HWHEELED.has(eventFlags); }
    public boolean isMouseMove(){ return EventFlags.MOUSE_MOVED.has(eventFlags); }
    //endregion
    //region wheelValue
    private final int wheelValue;
    public int getWheelValue(){ return wheelValue; }
    //endregion

    public InputMouseEvent(Wincon.MOUSE_EVENT_RECORD mouseEventRecord){
        if( mouseEventRecord==null )throw new IllegalArgumentException("mouseEventRecord==null");
        x = mouseEventRecord.dwMousePosition.X;
        y = mouseEventRecord.dwMousePosition.Y;
        controlState = mouseEventRecord.dwControlKeyState;
        buttonState = mouseEventRecord.dwButtonState;
        eventFlags = mouseEventRecord.dwEventFlags;
        if( EventFlags.MOUSE_HWHEELED.has(eventFlags) ){
            wheelValue = mouseEventRecord.dwButtonState;
            wheelEvent = true;
        }else if( EventFlags.MOUSE_WHEELED.has(eventFlags) ){
            wheelValue = mouseEventRecord.dwButtonState;
            wheelEvent = true;
        }else{
            wheelValue = 0;
            wheelEvent = false;
        }
    }

    public String toString(){
        var sb = new StringBuilder();
        var ls = new ArrayList<String>();
        ls.add("x="+x);
        ls.add("y="+y);
        ls.add("wheel="+getWheelValue());

        ls.add(isLeftButton() ? "LeftButton" : "");
        ls.add(isSecondButton() ? "ThirdButton" : "");
        ls.add(isThirdButton() ? "ThirdButton" : "");
        ls.add(isFourthButton() ? "FourthButton" : "");
        ls.add(isRightButton() ? "RightButton" : "");

        ls.add(isDoubleClick() ? "DoubleClick" : "");
        ls.add(isVerticalMouseWheel() ? "VerticalMouseWheel" : "");
        ls.add(isHorizontalMouseWheel() ? "HorizontalMouseWheel" : "");
        ls.add(isMouseMove() ? "MouseMove" : "");

        ls.add(isCapsLock() ? "CapsLock" : "");
        ls.add(isEnhanced() ? "Enhanced" : "");
        ls.add(isLeftAlt() ? "LeftAlt" : "");
        ls.add(isLeftCtrl() ? "LeftCtrl" : "");
        ls.add(isNumLock() ? "NumLock" : "");
        ls.add(isRightAlt() ? "RightAlt" : "");
        ls.add(isRightCtrl() ? "RightCtrl" : "");
        ls.add(isScrollLock() ? "ScrollLock" : "");
        ls.add(isShift() ? "Shift" : "");

        sb.append("InputMouseEvent{");
        sb.append(ls.stream().reduce("", (a,b)->a.length()>0 ? a+(b.length()>0 ? ","+b : "") : b));
        sb.append("}");
        return sb.toString();
    }
}
