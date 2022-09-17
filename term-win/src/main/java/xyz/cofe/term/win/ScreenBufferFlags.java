package xyz.cofe.term.win;

public enum ScreenBufferFlags implements BitFlag {
    /**
     * Characters written by the WriteFile or WriteConsole function or
     * echoed by the ReadFile or ReadConsole function are parsed for ASCII control sequences, and
     * the correct action is performed. Backspace, tab, bell, carriage return, and
     * line feed characters are processed.
     * <p>
     * It should be enabled when using control sequences or when ENABLE_VIRTUAL_TERMINAL_PROCESSING is set.
     */
    ENABLE_PROCESSED_OUTPUT(0x0001),

    /**
     * When writing with WriteFile or WriteConsole or echoing with ReadFile or ReadConsole,
     * the cursor moves to the beginning of the next row when it reaches the end of the current row.
     * This causes the rows displayed in the console window to scroll up automatically when
     * the cursor advances beyond the last row in the window.
     * <p>
     * It also causes the contents of the console screen buffer to
     * scroll up (../discarding the top row of the console screen buffer) when
     * the cursor advances beyond the last row in the console screen buffer.
     * <p>
     * If this mode is disabled, the last character in the row is overwritten with any subsequent characters.
     */
    ENABLE_WRAP_AT_EOL_OUTPUT(0x0002),

    /**
     * When writing with WriteFile or WriteConsole, characters are parsed for VT100 and
     * similar control character sequences that control cursor movement, color/font mode, and
     * other operations that can also be performed via the existing Console APIs.
     * <p>
     * For more information, see Console Virtual Terminal Sequences.
     * <p>
     * Ensure ENABLE_PROCESSED_OUTPUT is set when using this flag.
     */
    ENABLE_VIRTUAL_TERMINAL_PROCESSING(0x0004),

    /**
     * When writing with WriteFile or WriteConsole, this adds
     * an additional state to end-of-line wrapping that can delay the cursor move and buffer scroll operations.
     * <p>
     * Normally when ENABLE_WRAP_AT_EOL_OUTPUT is set and text reaches the end of the line,
     * the cursor will immediately move to the next line and the contents of
     * the buffer will scroll up by one line.
     * <p>
     * In contrast with this flag set, the cursor does not move to the next line,
     * and the scroll operation is not performed.
     * <p>
     * The written character will be printed in the final position on the line and
     * the cursor will remain above this character as if ENABLE_WRAP_AT_EOL_OUTPUT was off,
     * but the next printable character will be printed as if ENABLE_WRAP_AT_EOL_OUTPUT is on.
     * <p>
     * No overwrite will occur. Specifically, the cursor quickly advances down to the following line,
     * a scroll is performed if necessary, the character is printed, and the cursor advances one more position.
     * <p>
     * The typical usage of this flag is intended in conjunction with
     * setting ENABLE_VIRTUAL_TERMINAL_PROCESSING to better emulate
     * a terminal emulator where writing the final character on the screen (../in the bottom right corner)
     * without triggering an immediate scroll is the desired behavior.
     */
    DISABLE_NEWLINE_AUTO_RETURN(0x0008),

    /**
     * The APIs for writing character attributes including WriteConsoleOutput and WriteConsoleOutputAttribute
     * allow the usage of flags from character attributes to adjust the color of
     * the foreground and background of text. Additionally, a range of DBCS flags was specified with
     * the COMMON_LVB prefix.
     * <p>
     * Historically, these flags only functioned in DBCS code pages
     * for Chinese, Japanese, and Korean languages.
     * <p>
     * With exception of the leading byte and trailing byte flags, the remaining
     * flags describing line drawing and reverse video (../swap foreground and background colors)
     * can be useful for other languages to emphasize portions of output.
     * <p>
     * Setting this console mode flag will allow these attributes to be used in every code page on every language.
     * <p>
     * It is off by default to maintain compatibility with known applications that
     * have historically taken advantage of the console ignoring these flags on
     * non-CJK machines to store bits in these fields for their own purposes or by accident.
     * <p>
     * Note that using the ENABLE_VIRTUAL_TERMINAL_PROCESSING mode can result in
     * LVB grid and reverse video flags being set while this flag is still off
     * if the attached application requests underlining or inverse video via
     * Console Virtual Terminal Sequences.
     */
    ENABLE_LVB_GRID_WORLDWIDE(0x0010),
    ;
    private final int bitFlag;

    @Override
    public int bitFlag() {
        return bitFlag;
    }

    ScreenBufferFlags(int bitFlag) {
        this.bitFlag = bitFlag;
    }
}
