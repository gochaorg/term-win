package xyz.cofe.term.win;

// https://docs.microsoft.com/en-us/windows/console/getconsolemode
public class ConsoleMode {
    public static enum ScreenBufferFlags implements BitFlag {
        /**
         Characters written by the WriteFile or WriteConsole function or
         echoed by the ReadFile or ReadConsole function are parsed for ASCII control sequences, and
         the correct action is performed. Backspace, tab, bell, carriage return, and
         line feed characters are processed.

         It should be enabled when using control sequences or when ENABLE_VIRTUAL_TERMINAL_PROCESSING is set.
        */
        ENABLE_PROCESSED_OUTPUT(0x0001),

        /**
         When writing with WriteFile or WriteConsole or echoing with ReadFile or ReadConsole,
         the cursor moves to the beginning of the next row when it reaches the end of the current row.
         This causes the rows displayed in the console window to scroll up automatically when
         the cursor advances beyond the last row in the window.

         It also causes the contents of the console screen buffer to
         scroll up (../discarding the top row of the console screen buffer) when
         the cursor advances beyond the last row in the console screen buffer.

         If this mode is disabled, the last character in the row is overwritten with any subsequent characters.
        */
        ENABLE_WRAP_AT_EOL_OUTPUT(0x0002),

        /**
         When writing with WriteFile or WriteConsole, characters are parsed for VT100 and
         similar control character sequences that control cursor movement, color/font mode, and
         other operations that can also be performed via the existing Console APIs.

         For more information, see Console Virtual Terminal Sequences.

         Ensure ENABLE_PROCESSED_OUTPUT is set when using this flag.
        */
        ENABLE_VIRTUAL_TERMINAL_PROCESSING(0x0004),

        /**
         When writing with WriteFile or WriteConsole, this adds
         an additional state to end-of-line wrapping that can delay the cursor move and buffer scroll operations.

         Normally when ENABLE_WRAP_AT_EOL_OUTPUT is set and text reaches the end of the line,
         the cursor will immediately move to the next line and the contents of
         the buffer will scroll up by one line.

         In contrast with this flag set, the cursor does not move to the next line,
         and the scroll operation is not performed.

         The written character will be printed in the final position on the line and
         the cursor will remain above this character as if ENABLE_WRAP_AT_EOL_OUTPUT was off,
         but the next printable character will be printed as if ENABLE_WRAP_AT_EOL_OUTPUT is on.

         No overwrite will occur. Specifically, the cursor quickly advances down to the following line,
         a scroll is performed if necessary, the character is printed, and the cursor advances one more position.

         The typical usage of this flag is intended in conjunction with
         setting ENABLE_VIRTUAL_TERMINAL_PROCESSING to better emulate
         a terminal emulator where writing the final character on the screen (../in the bottom right corner)
         without triggering an immediate scroll is the desired behavior.
        */
        DISABLE_NEWLINE_AUTO_RETURN(0x0008),

        /**
         The APIs for writing character attributes including WriteConsoleOutput and WriteConsoleOutputAttribute
         allow the usage of flags from character attributes to adjust the color of
         the foreground and background of text. Additionally, a range of DBCS flags was specified with
         the COMMON_LVB prefix.

         Historically, these flags only functioned in DBCS code pages
         for Chinese, Japanese, and Korean languages.

         With exception of the leading byte and trailing byte flags, the remaining
         flags describing line drawing and reverse video (../swap foreground and background colors)
         can be useful for other languages to emphasize portions of output.

         Setting this console mode flag will allow these attributes to be used in every code page on every language.

         It is off by default to maintain compatibility with known applications that
         have historically taken advantage of the console ignoring these flags on
         non-CJK machines to store bits in these fields for their own purposes or by accident.

         Note that using the ENABLE_VIRTUAL_TERMINAL_PROCESSING mode can result in
         LVB grid and reverse video flags being set while this flag is still off
         if the attached application requests underlining or inverse video via
         Console Virtual Terminal Sequences.
        */
        ENABLE_LVB_GRID_WORLDWIDE(0x0010),
        ;
        private final int bitFlag;

        @Override
        public int bitFlag() {
            return bitFlag;
        }

        ScreenBufferFlags(int bitFlag){
            this.bitFlag = bitFlag;
        }
    }
    public static enum InputFlags implements BitFlag {
        /**
         Characters read by the ReadFile or ReadConsole function are
         written to the active screen buffer as they are typed into the console.

         This mode can be used only if the ENABLE_LINE_INPUT mode is also enabled.
        */
        ENABLE_ECHO_INPUT(0x0004),

        /**
         When enabled, text entered in a console window will be inserted at
         the current cursor location and all text following that location will not be overwritten.

         When disabled, all following text will be overwritten.
        */
        ENABLE_INSERT_MODE(0x0020),

        /**
         The ReadFile or ReadConsole function returns only when
         a carriage return character is read.

         If this mode is disabled, the functions return when one or more characters are available.
        */
        ENABLE_LINE_INPUT(0x0002),

        /**
         If the mouse pointer is within the borders of the console window and
         the window has the keyboard focus, mouse events generated by mouse movement and
         button presses are placed in the input buffer.

         These events are discarded by ReadFile or ReadConsole, even when this mode is enabled.

         The ReadConsoleInput function can be used to read MOUSE_EVENT input records from the input buffer.
        */
        ENABLE_MOUSE_INPUT(0x0010),

        /**
         CTRL+C is processed by the system and is not placed in the input buffer.

         If the input buffer is being read by ReadFile or ReadConsole, other control keys are
         processed by the system and are not returned in the ReadFile or ReadConsole buffer.

         If the ENABLE_LINE_INPUT mode is also enabled, backspace, carriage return,
         and line feed characters are handled by the system.
        */
        ENABLE_PROCESSED_INPUT(0x0001),

        /**
         This flag enables the user to use the mouse to select and edit text.

         To enable this mode, use ENABLE_QUICK_EDIT_MODE | ENABLE_EXTENDED_FLAGS.

         To disable this mode, use ENABLE_EXTENDED_FLAGS without this flag.
        */
        ENABLE_QUICK_EDIT_MODE(0x0040),

        /**
         User interactions that change the size of the console screen buffer are
         reported in the console's input buffer.

         Information about these events can be read from the input buffer
         by applications using the ReadConsoleInput function,
         but not by those using ReadFile or ReadConsole.
        */
        ENABLE_WINDOW_INPUT(0x0008),

        /**
         Setting this flag directs the Virtual Terminal processing engine
         to convert user input received by the console window into Console Virtual Terminal Sequences that
         can be retrieved by a supporting application through ReadFile or ReadConsole functions.

         The typical usage of this flag is intended in conjunction with ENABLE_VIRTUAL_TERMINAL_PROCESSING on
         the output handle to connect to an application
         that communicates exclusively via virtual terminal sequences.
        */
        ENABLE_VIRTUAL_TERMINAL_INPUT(0x0200)
        ;

        private final int bitFlag;

        @Override
        public int bitFlag() {
            return bitFlag;
        }

        InputFlags(int bitFlag){
            this.bitFlag = bitFlag;
        }
    }

    public ConsoleMode( int outputFlags, int errorFlags, int inputFlags ){
        this.outputFlags = outputFlags;
        this.errorFlags = errorFlags;
        this.inputFlags = inputFlags;
    }

    private int outputFlags;
    public int getOutputFlags(){ return outputFlags; }

    private int errorFlags;
    public int getErrorFlags(){ return errorFlags; }

    private int inputFlags;
    public int getInputFlags(){ return inputFlags; }

    //region ENABLE_PROCESSED_OUTPUT
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_PROCESSED_OUTPUT}
     * @return value
     */
    public boolean isEnableProcessedOutput(){ return ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.has(outputFlags); }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_PROCESSED_OUTPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableProcessedOutput(boolean switchOn){
        return new ConsoleMode(ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.update(outputFlags,switchOn), errorFlags, inputFlags);
    }
    //endregion
    //region ENABLE_PROCESSED_OUTPUT StdError
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_PROCESSED_OUTPUT} for stdErr
     * @return value
     */
    public boolean isEnableProcessedError(){ return ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.has(errorFlags); }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_PROCESSED_OUTPUT} for stdErr
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableProcessedError(boolean switchOn){
        return new ConsoleMode(outputFlags,ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.update(errorFlags,switchOn), inputFlags);
    }
    //endregion

    //region ENABLE_WRAP_AT_EOL_OUTPUT
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     * @return value
     */
    public boolean isEnableWrapAtEolOutput(){
        return ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.has(outputFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableWrapAtEolOutput(boolean switchOn){
        return new ConsoleMode(ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.update(outputFlags,switchOn), errorFlags, inputFlags);
    }
    //endregion
    //region ENABLE_WRAP_AT_EOL_OUTPUT StdErr
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     * @return value
     */
    public boolean isEnableWrapAtEolError(){
        return ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.has(errorFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableWrapAtEolError(boolean switchOn){
        return new ConsoleMode(outputFlags, ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.update(errorFlags,switchOn), inputFlags);
    }
    //endregion

    //region ENABLE_VIRTUAL_TERMINAL_PROCESSING
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     * @return value
     */
    public boolean isEnableVirtualTerminalProcessing(){
        return ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.has(outputFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableVirtualTerminalProcessing(boolean switchOn){
        return new ConsoleMode(ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.update(outputFlags,switchOn), errorFlags, inputFlags);
    }
    //endregion
    //region ENABLE_VIRTUAL_TERMINAL_PROCESSING StdErr
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     * @return value
     */
    public boolean isEnableVirtualTerminalProcessingError(){
        return ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.has(errorFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableVirtualTerminalProcessingError(boolean switchOn){
        return new ConsoleMode(outputFlags, ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.update(errorFlags,switchOn), inputFlags);
    }
    //endregion

    //region DISABLE_NEWLINE_AUTO_RETURN
    /**
     * read flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     * @return value
     */
    public boolean isDisableNewLineAutoReturn(){
        return ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.has(outputFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     * @param disable value
     * @return new mode with switched flag
     */
    public ConsoleMode disableNewLineAutoReturn(boolean disable){
        return new ConsoleMode(ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.update(outputFlags,disable),errorFlags, inputFlags);
    }
    //endregion
    //region DISABLE_NEWLINE_AUTO_RETURN StdErr
    /**
     * read flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     * @return value
     */
    public boolean isDisableNewLineAutoReturnError(){
        return ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.has(errorFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     * @param disable value
     * @return new mode with switched flag
     */
    public ConsoleMode disableNewLineAutoReturnError(boolean disable){
        return new ConsoleMode(outputFlags,ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.update(errorFlags,disable), inputFlags);
    }
    //endregion

    //region ENABLE_LVB_GRID_WORLDWIDE
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * @return value
     */
    public boolean isEnableLVBGridWorldWide(){
        return ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.has(outputFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableLVBGridWorldWide(boolean switchOn){
        return new ConsoleMode(ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.update(outputFlags,switchOn),errorFlags, inputFlags);
    }
    //endregion
    //region ENABLE_LVB_GRID_WORLDWIDE StdErr
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * @return value
     */
    public boolean isEnableLVBGridWorldWideError(){
        return ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.has(errorFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableLVBGridWorldWideError(boolean switchOn){
        return new ConsoleMode(outputFlags,ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.update(errorFlags,switchOn), inputFlags);
    }
    //endregion

    //region ENABLE_ECHO_INPUT
    /**
     * read flag {@link InputFlags#ENABLE_ECHO_INPUT}
     * @return value
     */
    public boolean isEnableEchoInput(){
        return InputFlags.ENABLE_ECHO_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputFlags#ENABLE_ECHO_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableEchoInput(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags,InputFlags.ENABLE_ECHO_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_INSERT_MODE
    /**
     * read flag {@link InputFlags#ENABLE_INSERT_MODE}
     * @return value
     */
    public boolean isEnableInsertMode(){
        return InputFlags.ENABLE_INSERT_MODE.has(inputFlags);
    }

    /**
     * switch flag {@link InputFlags#ENABLE_INSERT_MODE}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableInsertMode(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags,InputFlags.ENABLE_INSERT_MODE.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_LINE_INPUT
    /**
     * read flag {@link InputFlags#ENABLE_LINE_INPUT}
     * @return value
     */
    public boolean isEnableLineInput(){
        return InputFlags.ENABLE_LINE_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputFlags#ENABLE_LINE_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableLineInput(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags,InputFlags.ENABLE_LINE_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_MOUSE_INPUT
    /**
     * read flag {@link InputFlags#ENABLE_MOUSE_INPUT}
     * @return value
     */
    public boolean isEnableMouseInput(){
        return InputFlags.ENABLE_MOUSE_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputFlags#ENABLE_MOUSE_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableMouseInput(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags,InputFlags.ENABLE_MOUSE_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_PROCESSED_INPUT
    /**
     * read flag {@link InputFlags#ENABLE_PROCESSED_INPUT}
     * @return value
     */
    public boolean isEnableProcessedInput(){
        return InputFlags.ENABLE_PROCESSED_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputFlags#ENABLE_PROCESSED_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableProcessedInput(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags,InputFlags.ENABLE_PROCESSED_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_QUICK_EDIT_MODE
    /**
     * read flag {@link InputFlags#ENABLE_QUICK_EDIT_MODE}
     * @return value
     */
    public boolean isEnableQuickEditMode(){
        return InputFlags.ENABLE_QUICK_EDIT_MODE.has(inputFlags);
    }

    /**
     * switch flag {@link InputFlags#ENABLE_QUICK_EDIT_MODE}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableQuickEditMode(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags,InputFlags.ENABLE_QUICK_EDIT_MODE.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_WINDOW_INPUT
    /**
     * read flag {@link InputFlags#ENABLE_WINDOW_INPUT}
     * @return value
     */
    public boolean isEnableWindowInput(){
        return InputFlags.ENABLE_WINDOW_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputFlags#ENABLE_WINDOW_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableWindowInput(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags,InputFlags.ENABLE_WINDOW_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_VIRTUAL_TERMINAL_INPUT
    /**
     * read flag {@link InputFlags#ENABLE_VIRTUAL_TERMINAL_INPUT}
     * @return value
     */
    public boolean isEnableVirtualTerminalInput(){
        return InputFlags.ENABLE_VIRTUAL_TERMINAL_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputFlags#ENABLE_VIRTUAL_TERMINAL_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode enableVirtualTerminalInput(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags,InputFlags.ENABLE_VIRTUAL_TERMINAL_INPUT.update(inputFlags,switchOn));
    }
    //endregion
}
