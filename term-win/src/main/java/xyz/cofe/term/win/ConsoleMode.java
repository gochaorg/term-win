package xyz.cofe.term.win;

import java.util.stream.Stream;

// https://docs.microsoft.com/en-us/windows/console/getconsolemode
public class ConsoleMode {

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
    public boolean isOutputProcessing(){ return ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.has(outputFlags); }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_PROCESSED_OUTPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode outputProcessing(boolean switchOn){
        return new ConsoleMode(ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.update(outputFlags,switchOn), errorFlags, inputFlags);
    }
    //endregion
    //region ENABLE_PROCESSED_OUTPUT StdError
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_PROCESSED_OUTPUT} for stdErr
     * @return value
     */
    public boolean isErrorProcessing(){ return ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.has(errorFlags); }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_PROCESSED_OUTPUT} for stdErr
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode errorProcessing(boolean switchOn){
        return new ConsoleMode(outputFlags,ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.update(errorFlags,switchOn), inputFlags);
    }
    //endregion

    //region ENABLE_WRAP_AT_EOL_OUTPUT
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     * @return value
     */
    public boolean isOutputWrapAtEol(){
        return ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.has(outputFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode outputWrapAtEol(boolean switchOn){
        return new ConsoleMode(ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.update(outputFlags,switchOn), errorFlags, inputFlags);
    }
    //endregion
    //region ENABLE_WRAP_AT_EOL_OUTPUT StdErr
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     * @return value
     */
    public boolean isErrorWrapAtEol(){
        return ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.has(errorFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode errorWrapAtEol(boolean switchOn){
        return new ConsoleMode(outputFlags, ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.update(errorFlags,switchOn), inputFlags);
    }
    //endregion

    //region ENABLE_VIRTUAL_TERMINAL_PROCESSING
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     * @return value
     */
    public boolean isOutputVirtualTerminalProcessing(){
        return ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.has(outputFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode outputVirtualTerminalProcessing(boolean switchOn){
        return new ConsoleMode(ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.update(outputFlags,switchOn), errorFlags, inputFlags);
    }
    //endregion
    //region ENABLE_VIRTUAL_TERMINAL_PROCESSING StdErr
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     * @return value
     */
    public boolean isErrorVirtualTerminalProcessing(){
        return ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.has(errorFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode errorVirtualTerminalProcessing(boolean switchOn){
        return new ConsoleMode(outputFlags, ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.update(errorFlags,switchOn), inputFlags);
    }
    //endregion

    //region DISABLE_NEWLINE_AUTO_RETURN
    /**
     * read flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     * @return value
     */
    public boolean isOutputNewLineAutoReturn(){
        return !ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.has(outputFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     * @param enable value
     * @return new mode with switched flag
     */
    public ConsoleMode outputNewLineAutoReturn(boolean enable){
        return new ConsoleMode(ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.update(outputFlags,!enable),errorFlags, inputFlags);
    }
    //endregion
    //region DISABLE_NEWLINE_AUTO_RETURN StdErr
    /**
     * read flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     * @return value
     */
    public boolean isErrorNewLineAutoReturn(){
        return !ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.has(errorFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     * @param enable value
     * @return new mode with switched flag
     */
    public ConsoleMode errorNewLineAutoReturn(boolean enable){
        return new ConsoleMode(outputFlags,ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.update(errorFlags,!enable), inputFlags);
    }
    //endregion

    //region ENABLE_LVB_GRID_WORLDWIDE
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * @return value
     */
    public boolean isOutputLVBGridWorldWide(){
        return ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.has(outputFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode outputLVBGridWorldWide(boolean switchOn){
        return new ConsoleMode(ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.update(outputFlags,switchOn),errorFlags, inputFlags);
    }
    //endregion
    //region ENABLE_LVB_GRID_WORLDWIDE StdErr
    /**
     * read flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * @return value
     */
    public boolean isErrorLVBGridWorldWide(){
        return ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.has(errorFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * @param switchOn value
     * @return new mode with switched flag
     */
    public ConsoleMode errorLVBGridWorldWide(boolean switchOn){
        return new ConsoleMode(outputFlags,ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.update(errorFlags,switchOn), inputFlags);
    }
    //endregion

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
    public ConsoleMode inputEcho(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags, InputModeFlags.ENABLE_ECHO_INPUT.update(inputFlags,switchOn));
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
    public ConsoleMode inputInsert(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags, InputModeFlags.ENABLE_INSERT_MODE.update(inputFlags,switchOn));
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
    public ConsoleMode inputLine(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags, InputModeFlags.ENABLE_LINE_INPUT.update(inputFlags,switchOn));
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
    public ConsoleMode inputMouse(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags, InputModeFlags.ENABLE_MOUSE_INPUT.update(inputFlags,switchOn));
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
    public ConsoleMode inputProcessing(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags, InputModeFlags.ENABLE_PROCESSED_INPUT.update(inputFlags,switchOn));
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
    public ConsoleMode inputQuickEdit(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags, InputModeFlags.ENABLE_QUICK_EDIT_MODE.update(inputFlags,switchOn));
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
    public ConsoleMode inputWindow(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags, InputModeFlags.ENABLE_WINDOW_INPUT.update(inputFlags,switchOn));
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
    public ConsoleMode inputVirtualTerminal(boolean switchOn){
        return new ConsoleMode(outputFlags,errorFlags, InputModeFlags.ENABLE_VIRTUAL_TERMINAL_INPUT.update(inputFlags,switchOn));
    }
    //endregion

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ConsoleMode{");
        sb.append("output=");
        sb.append(Stream.of(
            isOutputProcessing()?"Processing":"",
            isOutputWrapAtEol()?"WrapAtEol":"",
            isOutputVirtualTerminalProcessing()?"VirtualTermProcessing":"",
            isOutputNewLineAutoReturn()?"NewLineAutoReturn":"",
            isOutputLVBGridWorldWide()?"LVBGridWorldWide":""
        ).reduce("", (a, b)-> a.length()>0 ? a+","+b : b));
        sb.append(",error=");
        sb.append(Stream.of(
                isErrorProcessing()?"Processing":"",
                isErrorWrapAtEol()?"WrapAtEol":"",
                isErrorVirtualTerminalProcessing()?"VirtualTermProcessing":"",
                isErrorNewLineAutoReturn()?"NewLineAutoReturn":"",
                isErrorLVBGridWorldWide()?"LVBGridWorldWide":""
        ).reduce("", (a, b)-> a.length()>0 ? a+","+b : b));
        sb.append(",input=");
        sb.append(Stream.of(
                isInputEcho()?"Echo":"",
                isInputInsert()?"Insert":"",
                isInputLine()?"Line":"",
                isInputMouse()?"Mouse":"",
                isInputProcessing()?"Processing":"",
                isInputQuickEdit()?"QuickEdit":"",
                isInputWindow()?"Window":"",
                isInputVirtualTerminal()?"VirtualTermProcessing":""
        ).reduce("", (a, b)-> a.length()>0 ? a+","+b : b));
        sb.append("}");
        return sb.toString();
    }
}
