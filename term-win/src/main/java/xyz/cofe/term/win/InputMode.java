package xyz.cofe.term.win;

import static xyz.cofe.term.win.impl.StringUtils.join;

public class InputMode {
    private final int inputFlags;
    public int getInputFlags(){ return inputFlags; }

    public InputMode(int inputFlags){
        this.inputFlags = inputFlags;
    }

    //region echo : boolean - ENABLE_ECHO_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_ECHO_INPUT}
     * @return value
     */
    public boolean isEcho(){
        return InputModeFlags.ENABLE_ECHO_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_ECHO_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode echo(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_ECHO_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region insert : boolean - ENABLE_INSERT_MODE
    /**
     * read flag {@link InputModeFlags#ENABLE_INSERT_MODE}
     * @return value
     */
    public boolean isInsert(){
        return InputModeFlags.ENABLE_INSERT_MODE.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_INSERT_MODE}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode insert(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_INSERT_MODE.update(inputFlags,switchOn));
    }
    //endregion
    //region line : boolean - ENABLE_LINE_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_LINE_INPUT}
     * @return value
     */
    public boolean isLine(){
        return InputModeFlags.ENABLE_LINE_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_LINE_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode line(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_LINE_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region mouse : boolean - ENABLE_MOUSE_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_MOUSE_INPUT}
     * @return value
     */
    public boolean isMouse(){
        return InputModeFlags.ENABLE_MOUSE_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_MOUSE_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode mouse(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_MOUSE_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region processing : boolean - ENABLE_PROCESSED_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_PROCESSED_INPUT}
     * @return value
     */
    public boolean isProcessing(){
        return InputModeFlags.ENABLE_PROCESSED_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_PROCESSED_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode processing(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_PROCESSED_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region quickEdit : boolean - ENABLE_QUICK_EDIT_MODE
    /**
     * read flag {@link InputModeFlags#ENABLE_QUICK_EDIT_MODE}
     * @return value
     */
    public boolean isQuickEdit(){
        return InputModeFlags.ENABLE_QUICK_EDIT_MODE.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_QUICK_EDIT_MODE}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode quickEdit(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_QUICK_EDIT_MODE.update(inputFlags,switchOn));
    }
    //endregion
    //region window : boolean - ENABLE_WINDOW_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_WINDOW_INPUT}
     * @return value
     */
    public boolean isWindow(){
        return InputModeFlags.ENABLE_WINDOW_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_WINDOW_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode window(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_WINDOW_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region virtualTerminal : boolean - ENABLE_VIRTUAL_TERMINAL_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_VIRTUAL_TERMINAL_INPUT}
     * @return value
     */
    public boolean isVirtualTerminal(){
        return InputModeFlags.ENABLE_VIRTUAL_TERMINAL_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_VIRTUAL_TERMINAL_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode virtualTerminal(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_VIRTUAL_TERMINAL_INPUT.update(inputFlags,switchOn));
    }
    //endregion

    public String toString(){
        return "InputMode{"+join(",",
            isEcho() ? "Echo" : "",
            isInsert() ? "Insert" : "",
            isLine() ? "Line" : "",
            isMouse() ? "Mouse" : "",
            isProcessing() ? "Processing" : "",
            isQuickEdit() ? "QuickEdit" : "",
            isWindow() ? "Window" : "",
            isVirtualTerminal() ? "VirtualTerminal" : ""
            )+"}";
    }
}
