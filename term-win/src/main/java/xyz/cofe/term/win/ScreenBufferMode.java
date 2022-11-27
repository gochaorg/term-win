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
     * <br>
     * Символы, записанные функцией WriteFile или WriteConsole или
     * отраженные функцией ReadFile или ReadConsole анализируются на наличие управляющих последовательностей ASCII, и
     * выполняется правильное действие. Backspace, табуляция, колокольчик, возврат каретки и
     * Обрабатываются символы перевода строки.
     * <br>
     * Должна быть включена при использовании управляющих последовательностей или при установке ENABLE_VIRTUAL_TERMINAL_PROCESSING.
     * @return value
     */
    public boolean isProcessing(){ return ScreenBufferFlags.ENABLE_PROCESSED_OUTPUT.has(screenBufferFlags); }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_PROCESSED_OUTPUT}
     * <br>
     * Символы, записанные функцией WriteFile или WriteConsole или
     * отраженные функцией ReadFile или ReadConsole анализируются на наличие управляющих последовательностей ASCII, и
     * выполняется правильное действие. Backspace, табуляция, колокольчик, возврат каретки и
     * Обрабатываются символы перевода строки.
     * <br>
     * Должна быть включена при использовании управляющих последовательностей или при установке ENABLE_VIRTUAL_TERMINAL_PROCESSING.
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
     * <br>
     * При записи с помощью WriteFile или WriteConsole или отображении с помощью ReadFile или ReadConsole,
     * курсор перемещается в начало следующей строки, когда он достигает конца текущей строки.
     * Это приводит к тому, что строки, отображаемые в окне консоли, автоматически прокручиваются вверх, когда
     * Курсор перемещается за пределы последней строки в окне.
     * <br>
     * Это также приводит к тому, что содержимое экранного буфера консоли
     * прокрутить вверх (../отбросить верхнюю строку буфера экрана консоли), когда
     * курсор перемещается за пределы последней строки буфера экрана консоли.
     * <br>
     * Если этот режим отключен, последний символ в строке перезаписывается любыми последующими символами.
     * @return value
     */
    public boolean isWrapAtEol(){
        return ScreenBufferFlags.ENABLE_WRAP_AT_EOL_OUTPUT.has(screenBufferFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_WRAP_AT_EOL_OUTPUT}
     *
     * <br>
     * При записи с помощью WriteFile или WriteConsole или отображении с помощью ReadFile или ReadConsole,
     * курсор перемещается в начало следующей строки, когда он достигает конца текущей строки.
     * Это приводит к тому, что строки, отображаемые в окне консоли, автоматически прокручиваются вверх, когда
     * Курсор перемещается за пределы последней строки в окне.
     * <br>
     * Это также приводит к тому, что содержимое экранного буфера консоли
     * прокрутить вверх (../отбросить верхнюю строку буфера экрана консоли), когда
     * курсор перемещается за пределы последней строки буфера экрана консоли.
     * <br>
     * Если этот режим отключен, последний символ в строке перезаписывается любыми последующими символами.
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
     *
     * <br>
     * При записи с помощью WriteFile или WriteConsole символы анализируются для VT100 и
     * аналогичные последовательности управляющих символов, управляющие движением курсора, режимом цвета/шрифта и
     * другие операции, которые также можно выполнять с помощью существующих консольных API.
     *
     * <br>
     * Дополнительные сведения см. в разделе Последовательности виртуальных терминалов консоли.
     * <br>
     * Убедитесь, что ENABLE_PROCESSED_OUTPUT установлен при использовании этого флага.
     * @return value
     */
    public boolean isVirtualTerminalProcessing(){
        return ScreenBufferFlags.ENABLE_VIRTUAL_TERMINAL_PROCESSING.has(screenBufferFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_VIRTUAL_TERMINAL_PROCESSING}
     *
     * <br>
     * При записи с помощью WriteFile или WriteConsole символы анализируются для VT100 и
     * аналогичные последовательности управляющих символов, управляющие движением курсора, режимом цвета/шрифта и
     * другие операции, которые также можно выполнять с помощью существующих консольных API.
     *
     * <br>
     * Дополнительные сведения см. в разделе Последовательности виртуальных терминалов консоли.
     * <br>
     * Убедитесь, что ENABLE_PROCESSED_OUTPUT установлен при использовании этого флага.
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
     *
     * <br>
     * При записи с помощью WriteFile или WriteConsole это добавляет
     * дополнительное состояние переноса конца строки, которое может задерживать перемещение курсора и операции прокрутки буфера.
     * <br>
     * Обычно, когда ENABLE_WRAP_AT_EOL_OUTPUT установлен и текст достигает конца строки,
     * курсор сразу же переместится на следующую строку и содержимое
     * буфер будет прокручиваться вверх на одну строку.
     * <br>
     * В отличие от этого установленного флага, курсор не переходит на следующую строку,
     * и операция прокрутки не выполняется.
     * <br>
     * Написанный символ будет напечатан в последней позиции строки и
     * курсор останется над этим символом, как если бы ENABLE_WRAP_AT_EOL_OUTPUT был выключен,
     * но следующий печатный символ будет напечатан, как если бы ENABLE_WRAP_AT_EOL_OUTPUT был включен.
     * <br>
     * Никакой перезаписи не произойдет. В частности, курсор быстро переходит на следующую строку,
     * при необходимости выполняется прокрутка, печатается символ, и курсор перемещается еще на одну позицию.
     * <br>
     * Типичное использование этого флага предназначено в сочетании с
     * установка ENABLE_VIRTUAL_TERMINAL_PROCESSING для лучшей эмуляции
     * эмулятор терминала, в котором последний символ пишется на экране (../в правом нижнем углу)
     * без запуска немедленной прокрутки - желаемое поведение.
     * @return value
     */
    public boolean isNewLineAutoReturn(){
        return !ScreenBufferFlags.DISABLE_NEWLINE_AUTO_RETURN.has(screenBufferFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#DISABLE_NEWLINE_AUTO_RETURN}
     *
     * <br>
     * При записи с помощью WriteFile или WriteConsole это добавляет
     * дополнительное состояние переноса конца строки, которое может задерживать перемещение курсора и операции прокрутки буфера.
     * <br>
     * Обычно, когда ENABLE_WRAP_AT_EOL_OUTPUT установлен и текст достигает конца строки,
     * курсор сразу же переместится на следующую строку и содержимое
     * буфер будет прокручиваться вверх на одну строку.
     * <br>
     * В отличие от этого установленного флага, курсор не переходит на следующую строку,
     * и операция прокрутки не выполняется.
     * <br>
     * Написанный символ будет напечатан в последней позиции строки и
     * курсор останется над этим символом, как если бы ENABLE_WRAP_AT_EOL_OUTPUT был выключен,
     * но следующий печатный символ будет напечатан, как если бы ENABLE_WRAP_AT_EOL_OUTPUT был включен.
     * <br>
     * Никакой перезаписи не произойдет. В частности, курсор быстро переходит на следующую строку,
     * при необходимости выполняется прокрутка, печатается символ, и курсор перемещается еще на одну позицию.
     * <br>
     * Типичное использование этого флага предназначено в сочетании с
     * установка ENABLE_VIRTUAL_TERMINAL_PROCESSING для лучшей эмуляции
     * эмулятор терминала, в котором последний символ пишется на экране (../в правом нижнем углу)
     * без запуска немедленной прокрутки - желаемое поведение.
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
     * <br>
     * API для записи атрибутов символов, включая WriteConsoleOutput и WriteConsoleOutputAttribute.
     * разрешить использование флагов атрибутов персонажа для настройки цвета
     * передний план и фон текста. Кроме того, диапазон флагов DBCS был указан с помощью
     * префикс COMMON_LVB.
     * <br>
     * Исторически эти флаги функционировали только в кодовых страницах DBCS.
     * для китайского, японского и корейского языков.
     * <br>
     * За исключением флагов начального и конечного байтов, остальные
     * флаги, описывающие отрисовку линий и обратное видео (../поменять местами цвета переднего плана и фона)
     * может быть полезно для других языков, чтобы выделить части вывода.
     * <br>
     * Установка этого флага режима консоли позволит использовать эти атрибуты в каждой кодовой странице на любом языке.
     * <br>
     * По умолчанию отключено для обеспечения совместимости с известными приложениями, которые
     * исторически пользовались преимуществами игнорирования консолью этих флагов на
     * Машины, не относящиеся к CJK, для хранения битов в этих полях для собственных целей или случайно.
     * <br>
     * Обратите внимание, что использование режима ENABLE_VIRTUAL_TERMINAL_PROCESSING может привести к
     * Флаги сетки LVB и обратного видео устанавливаются, пока этот флаг все еще выключен
     * если прикрепленное приложение запрашивает подчеркивание или перевернутое видео через
     * Консольные последовательности виртуальных терминалов.
     *
     * @return value
     */
    public boolean isLVBGridWorldWide(){
        return ScreenBufferFlags.ENABLE_LVB_GRID_WORLDWIDE.has(screenBufferFlags);
    }

    /**
     * switch flag {@link ScreenBufferFlags#ENABLE_LVB_GRID_WORLDWIDE}
     * <br>
     * API для записи атрибутов символов, включая WriteConsoleOutput и WriteConsoleOutputAttribute.
     * разрешить использование флагов атрибутов персонажа для настройки цвета
     * передний план и фон текста. Кроме того, диапазон флагов DBCS был указан с помощью
     * префикс COMMON_LVB.
     * <br>
     * Исторически эти флаги функционировали только в кодовых страницах DBCS.
     * для китайского, японского и корейского языков.
     * <br>
     * За исключением флагов начального и конечного байтов, остальные
     * флаги, описывающие отрисовку линий и обратное видео (../поменять местами цвета переднего плана и фона)
     * может быть полезно для других языков, чтобы выделить части вывода.
     * <br>
     * Установка этого флага режима консоли позволит использовать эти атрибуты в каждой кодовой странице на любом языке.
     * <br>
     * По умолчанию отключено для обеспечения совместимости с известными приложениями, которые
     * исторически пользовались преимуществами игнорирования консолью этих флагов на
     * Машины, не относящиеся к CJK, для хранения битов в этих полях для собственных целей или случайно.
     * <br>
     * Обратите внимание, что использование режима ENABLE_VIRTUAL_TERMINAL_PROCESSING может привести к
     * Флаги сетки LVB и обратного видео устанавливаются, пока этот флаг все еще выключен
     * если прикрепленное приложение запрашивает подчеркивание или перевернутое видео через
     * Консольные последовательности виртуальных терминалов.
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
