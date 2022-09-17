package xyz.cofe.term.win;

public class ScreenBufferMode {
    private final int screenBufferFlags;
    public int getScreenBufferFlags(){ return screenBufferFlags; }

    public ScreenBufferMode(int screenBufferFlags){
        this.screenBufferFlags = screenBufferFlags;
    }

    //region ENABLE_PROCESSED_OUTPUT
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_PROCESSED_OUTPUT}
     * @return value
     */
    public boolean isOutputProcessing(){ return ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.has(screenBufferFlags); }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_PROCESSED_OUTPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ScreenBufferMode outputProcessing(boolean switchOn){
        return new ScreenBufferMode(ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.update(screenBufferFlags,switchOn));
    }
    //endregion
    //region ENABLE_WRAP_AT_EOL_OUTPUT
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     * @return value
     */
    public boolean isOutputWrapAtEol(){
        return ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.has(screenBufferFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ScreenBufferMode outputWrapAtEol(boolean switchOn){
        return new ScreenBufferMode(ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.update(screenBufferFlags,switchOn));
    }
    //endregion
    //region ENABLE_VIRTUAL_TERMINAL_PROCESSING
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     * @return value
     */
    public boolean isOutputVirtualTerminalProcessing(){
        return ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.has(screenBufferFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ScreenBufferMode outputVirtualTerminalProcessing(boolean switchOn){
        return new ScreenBufferMode(ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.update(screenBufferFlags,switchOn));
    }
    //endregion
    //region DISABLE_NEWLINE_AUTO_RETURN
    /**
     * read flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     * @return value
     */
    public boolean isOutputNewLineAutoReturn(){
        return !ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.has(screenBufferFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     * @param enable value
     * @return new mode with switched flag
     */
    public ScreenBufferMode outputNewLineAutoReturn(boolean enable){
        return new ScreenBufferMode(ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.update(screenBufferFlags,!enable));
    }
    //endregion
    //region ENABLE_LVB_GRID_WORLDWIDE
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * @return value
     */
    public boolean isOutputLVBGridWorldWide(){
        return ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.has(screenBufferFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ScreenBufferMode outputLVBGridWorldWide(boolean switchOn){
        return new ScreenBufferMode(ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.update(screenBufferFlags,switchOn));
    }
    //endregion
}
