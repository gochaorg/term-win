package xyz.cofe.term.win;

import xyz.cofe.term.win.impl.StringUtils;

public class ScreenBufferMode {
    private final int screenBufferFlags;
    public int getScreenBufferFlags(){ return screenBufferFlags; }

    public ScreenBufferMode(int screenBufferFlags){
        this.screenBufferFlags = screenBufferFlags;
    }

    //region processing : boolean ENABLE_PROCESSED_OUTPUT
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_PROCESSED_OUTPUT}
     * @return value
     */
    public boolean isProcessing(){ return ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.has(screenBufferFlags); }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_PROCESSED_OUTPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ScreenBufferMode processing(boolean switchOn){
        return new ScreenBufferMode(ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.update(screenBufferFlags,switchOn));
    }
    //endregion
    //region wrapAtEol : boolean ENABLE_WRAP_AT_EOL_OUTPUT
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     * @return value
     */
    public boolean isWrapAtEol(){
        return ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.has(screenBufferFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ScreenBufferMode wrapAtEol(boolean switchOn){
        return new ScreenBufferMode(ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.update(screenBufferFlags,switchOn));
    }
    //endregion
    //region virtualTerminalProcessing : boolean  ENABLE_VIRTUAL_TERMINAL_PROCESSING
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     * @return value
     */
    public boolean isVirtualTerminalProcessing(){
        return ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.has(screenBufferFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ScreenBufferMode virtualTerminalProcessing(boolean switchOn){
        return new ScreenBufferMode(ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.update(screenBufferFlags,switchOn));
    }
    //endregion
    //region newLineAutoReturn : Boolean DISABLE_NEWLINE_AUTO_RETURN
    /**
     * read flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     * @return value
     */
    public boolean isNewLineAutoReturn(){
        return !ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.has(screenBufferFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     * @param enable value
     * @return new mode with switched flag
     */
    public ScreenBufferMode newLineAutoReturn(boolean enable){
        return new ScreenBufferMode(ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.update(screenBufferFlags,!enable));
    }
    //endregion
    //region lvbGridWorldWide : Boolean  ENABLE_LVB_GRID_WORLDWIDE
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * @return value
     */
    public boolean isLVBGridWorldWide(){
        return ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.has(screenBufferFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ScreenBufferMode lvbGridWorldWide(boolean switchOn){
        return new ScreenBufferMode(ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.update(screenBufferFlags,switchOn));
    }
    //endregion

    public String toString(){
        return "ScreenBufferMode{"+StringUtils.join(",",
            isProcessing() ? "Processing" : "",
            isWrapAtEol() ? "WrapAtEol" : "",
            isVirtualTerminalProcessing() ? "VirtualTerminalProcessing" : "",
            isNewLineAutoReturn() ? "NewLineAutoReturn" : "",
            isLVBGridWorldWide() ? "LVBGridWorldWide" : ""
        )+"}";
    }
}
