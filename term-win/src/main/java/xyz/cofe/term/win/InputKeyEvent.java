package xyz.cofe.term.win;

import com.sun.jna.platform.win32.Wincon;

/**
 * <table>
 *     <caption>codes of keys</caption>
 *     <thead style="font-weight:bold">
 *         <tr>
 *            <td>KeyName</td>
 *            <td>keyCode</td>
 *            <td>scanCode</td>
 *         </tr>
 *     </thead>
 *     <tr><td> F1         </td><td> 112  </td><td> 59 </td></tr>
 *     <tr><td> F2         </td><td> 113  </td><td> 60 </td></tr>
 *     <tr><td> F3         </td><td> 114  </td><td> 61 </td></tr>
 *     <tr><td> F4         </td><td> 115  </td><td> 62 </td></tr>
 *     <tr><td> F5         </td><td> 116  </td><td> 63 </td></tr>
 *     <tr><td> F6         </td><td> 117  </td><td> 64 </td></tr>
 *     <tr><td> F7         </td><td> 118  </td><td> 65 </td></tr>
 *     <tr><td> F8         </td><td> 119  </td><td> 66 </td></tr>
 *     <tr><td> F9         </td><td> 120  </td><td> 67 </td></tr>
 *     <tr><td> F10        </td><td> 121  </td><td> 68 </td></tr>
 *     <tr><td> F11        </td><td> 122  </td><td> 87 </td></tr>
 *     <tr><td> F12        </td><td> 123  </td><td> 88 </td></tr>
 *     <tr><td> Escape     </td><td> 27   </td><td> 1  </td></tr>
 *     <tr><td> Backspace  </td><td> 8    </td><td> 14 </td></tr>
 *     <tr><td> ArrowLeft  </td><td> 37   </td><td> 75 </td></tr>
 *     <tr><td> ArrowRight </td><td> 39   </td><td> 77 </td></tr>
 *     <tr><td> ArrowUp    </td><td> 38   </td><td> 72 </td></tr>
 *     <tr><td> ArrowDown  </td><td> 40   </td><td> 80 </td></tr>
 *     <tr><td> Insert     </td><td> 45   </td><td> 82 </td></tr>
 *     <tr><td> Delete     </td><td> 46   </td><td> 83 </td></tr>
 *     <tr><td> Home       </td><td> 36   </td><td> 71 </td></tr>
 *     <tr><td> End        </td><td> 35   </td><td> 79 </td></tr>
 *     <tr><td> PageUp     </td><td> 33   </td><td> 73 </td></tr>
 *     <tr><td> PageDown   </td><td> 34   </td><td> 81 </td></tr>
 *     <tr><td> Tab        </td><td> 9    </td><td> 15 </td></tr>
 *     <tr><td> ReverseTab </td><td>      </td><td>    </td></tr>
 *     <tr><td> Enter      </td><td> 13   </td><td> 28 </td></tr>
 * </table>
 */
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
                ", character='" + character+ "'" +
                ", character.dec=" + ((int)character) +
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
