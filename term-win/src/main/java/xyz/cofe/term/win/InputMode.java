package xyz.cofe.term.win;

public class InputMode {
    private final int inputFlags;
    public int getInputFlags(){ return inputFlags; }

    public InputMode(int inputFlags){
        this.inputFlags = inputFlags;
    }

    //region ENABLE_ECHO_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_ECHO_INPUT}
     * @return value
     */
    public boolean isInputEcho(){
        return InputModeFlags.ENABLE_ECHO_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_ECHO_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode inputEcho(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_ECHO_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_INSERT_MODE
    /**
     * read flag {@link InputModeFlags#ENABLE_INSERT_MODE}
     * @return value
     */
    public boolean isInputInsert(){
        return InputModeFlags.ENABLE_INSERT_MODE.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_INSERT_MODE}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode inputInsert(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_INSERT_MODE.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_LINE_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_LINE_INPUT}
     * @return value
     */
    public boolean isInputLine(){
        return InputModeFlags.ENABLE_LINE_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_LINE_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode inputLine(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_LINE_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_MOUSE_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_MOUSE_INPUT}
     * @return value
     */
    public boolean isInputMouse(){
        return InputModeFlags.ENABLE_MOUSE_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_MOUSE_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode inputMouse(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_MOUSE_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_PROCESSED_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_PROCESSED_INPUT}
     * @return value
     */
    public boolean isInputProcessing(){
        return InputModeFlags.ENABLE_PROCESSED_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_PROCESSED_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode inputProcessing(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_PROCESSED_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_QUICK_EDIT_MODE
    /**
     * read flag {@link InputModeFlags#ENABLE_QUICK_EDIT_MODE}
     * @return value
     */
    public boolean isInputQuickEdit(){
        return InputModeFlags.ENABLE_QUICK_EDIT_MODE.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_QUICK_EDIT_MODE}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode inputQuickEdit(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_QUICK_EDIT_MODE.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_WINDOW_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_WINDOW_INPUT}
     * @return value
     */
    public boolean isInputWindow(){
        return InputModeFlags.ENABLE_WINDOW_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_WINDOW_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode inputWindow(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_WINDOW_INPUT.update(inputFlags,switchOn));
    }
    //endregion
    //region ENABLE_VIRTUAL_TERMINAL_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_VIRTUAL_TERMINAL_INPUT}
     * @return value
     */
    public boolean isInputVirtualTerminal(){
        return InputModeFlags.ENABLE_VIRTUAL_TERMINAL_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_VIRTUAL_TERMINAL_INPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public InputMode inputVirtualTerminal(boolean switchOn){
        return new InputMode(InputModeFlags.ENABLE_VIRTUAL_TERMINAL_INPUT.update(inputFlags,switchOn));
    }
    //endregion

}
