package xyz.cofe.term.win;

import static xyz.cofe.term.win.impl.StringUtils.join;

/**
 * Возвращает флаги управляюще вводом
 */
public class InputMode {
    private final int inputFlags;
    public int getInputFlags(){ return inputFlags; }

    public InputMode(int inputFlags){
        this.inputFlags = inputFlags;
    }

    //region echo : boolean - ENABLE_ECHO_INPUT
    /**
     * read flag {@link InputModeFlags#ENABLE_ECHO_INPUT}
     *
     * <br>
     * Символы, прочитанные функцией ReadFile или ReadConsole,
     * записываются в активный экранный буфер по мере ввода в консоль.
     *
     * <br>
     * Этот режим можно использовать, только если также включен режим ENABLE_LINE_INPUT.
     *
     * @return value
     */
    public boolean isEcho(){
        return InputModeFlags.ENABLE_ECHO_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_ECHO_INPUT}
     *
     * <br>
     * Символы, прочитанные функцией ReadFile или ReadConsole,
     * записываются в активный экранный буфер по мере ввода в консоль.
     *
     * <br>
     * Этот режим можно использовать, только если также включен режим ENABLE_LINE_INPUT.
     *
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
     *
     *
     * <br>
     * Если этот параметр включен, текст, введенный в окне консоли, будет вставлен в
     * текущее местоположение курсора и весь текст после этого местоположения не будут перезаписаны.
     *
     * <br>
     * Когда отключено, весь последующий текст будет перезаписан.
     *
     * @return value
     */
    public boolean isInsert(){
        return InputModeFlags.ENABLE_INSERT_MODE.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_INSERT_MODE}
     *
     * <br>
     * Если этот параметр включен, текст, введенный в окне консоли, будет вставлен в
     * текущее местоположение курсора и весь текст после этого местоположения не будут перезаписаны.
     *
     * <br>
     * Когда отключено, весь последующий текст будет перезаписан.
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
     *
     * <br>
     * Функция ReadFile или ReadConsole возвращает значение только тогда, когда
     * читается символ возврата каретки.
     *
     * <br>
     * Если этот режим отключен, функции возвращаются, когда доступны один или несколько символов.
     *
     * @return value
     */
    public boolean isLine(){
        return InputModeFlags.ENABLE_LINE_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_LINE_INPUT}
     *
     * <br>
     * Функция ReadFile или ReadConsole возвращает значение только тогда, когда
     * читается символ возврата каретки.
     *
     * <br>
     * Если этот режим отключен, функции возвращаются, когда доступны один или несколько символов.
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
     *
     * <br>
     * Если указатель мыши находится в пределах границ окна консоли и
     * окно имеет фокус клавиатуры, события мыши генерируются движением мыши и
     * нажатия кнопок помещаются в буфер ввода.
     * <br>
     * Эти события отбрасываются ReadFile или ReadConsole, даже если этот режим включен.
     * <br>
     * Функцию ReadConsoleInput можно использовать для чтения входных записей MOUSE_EVENT из входного буфера.
     * @return value
     */
    public boolean isMouse(){
        return InputModeFlags.ENABLE_MOUSE_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_MOUSE_INPUT}
     *
     * <br>
     * Если указатель мыши находится в пределах границ окна консоли и
     * окно имеет фокус клавиатуры, события мыши генерируются движением мыши и
     * нажатия кнопок помещаются в буфер ввода.
     * <br>
     * Эти события отбрасываются ReadFile или ReadConsole, даже если этот режим включен.
     * <br>
     * Функцию ReadConsoleInput можно использовать для чтения входных записей MOUSE_EVENT из входного буфера.
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
     *
     * <br>
     * CTRL+C обрабатывается системой и не помещается в буфер ввода.
     *
     * <br>
     * Если входной буфер считывается с помощью ReadFile или ReadConsole, другие клавиши управления
     * обрабатываются системой и не возвращаются в буфер ReadFile или ReadConsole.
     *
     * <br>
     * Если также включен режим ENABLE_LINE_INPUT, Backspace, возврат каретки,
     * и символы перевода строки обрабатываются системой.
     * @return value
     */
    public boolean isProcessing(){
        return InputModeFlags.ENABLE_PROCESSED_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_PROCESSED_INPUT}
     *
     * <br>
     * CTRL+C обрабатывается системой и не помещается в буфер ввода.
     *
     * <br>
     * Если входной буфер считывается с помощью ReadFile или ReadConsole, другие клавиши управления
     * обрабатываются системой и не возвращаются в буфер ReadFile или ReadConsole.
     *
     * <br>
     * Если также включен режим ENABLE_LINE_INPUT, Backspace, возврат каретки,
     * и символы перевода строки обрабатываются системой.
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
     *
     * <br>
     * Этот флаг позволяет пользователю использовать мышь для выбора и редактирования текста.
     * <br>
     * Чтобы включить этот режим, используйте ENABLE_QUICK_EDIT_MODE | ENABLE_EXTENDED_FLAGS.
     * <br>
     * Чтобы отключить этот режим, используйте ENABLE_EXTENDED_FLAGS без этого флага.
     * @return value
     */
    public boolean isQuickEdit(){
        return InputModeFlags.ENABLE_QUICK_EDIT_MODE.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_QUICK_EDIT_MODE}
     *
     * <br>
     * Этот флаг позволяет пользователю использовать мышь для выбора и редактирования текста.
     * <br>
     * Чтобы включить этот режим, используйте ENABLE_QUICK_EDIT_MODE | ENABLE_EXTENDED_FLAGS.
     * <br>
     * Чтобы отключить этот режим, используйте ENABLE_EXTENDED_FLAGS без этого флага.
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
     *
     *  <br>
     *  Взаимодействия с пользователем, которые изменяют размер экранного буфера консоли,
     *  сообщается в буфере ввода консоли.
     *
     *  <br>
     *  Информация об этих событиях может быть прочитана из входного буфера
     *  приложениями, использующими функцию ReadConsoleInput,
     *  но не теми, кто использует ReadFile или ReadConsole.
     *
     * @return value
     */
    public boolean isWindow(){
        return InputModeFlags.ENABLE_WINDOW_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_WINDOW_INPUT}
     *
     *  <br>
     *  Взаимодействия с пользователем, которые изменяют размер экранного буфера консоли,
     *  сообщается в буфере ввода консоли.
     *
     *  <br>
     *  Информация об этих событиях может быть прочитана из входного буфера
     *  приложениями, использующими функцию ReadConsoleInput,
     *  но не теми, кто использует ReadFile или ReadConsole.
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
     *
     * <br>
     * Установка этого флага направляет механизм обработки виртуального терминала.
     * для преобразования пользовательского ввода, полученного окном консоли, в последовательности виртуального терминала консоли, которые
     * может быть получен вспомогательным приложением с помощью функций ReadFile или ReadConsole.
     *
     * <br>
     * Типичное использование этого флага предполагается в сочетании с ENABLE_VIRTUAL_TERMINAL_PROCESSING на
     * дескриптор вывода для подключения к приложению
     * который общается исключительно через последовательности виртуальных терминалов.
     * @return value
     */
    public boolean isVirtualTerminal(){
        return InputModeFlags.ENABLE_VIRTUAL_TERMINAL_INPUT.has(inputFlags);
    }

    /**
     * switch flag {@link InputModeFlags#ENABLE_VIRTUAL_TERMINAL_INPUT}
     * <br>
     * Установка этого флага направляет механизм обработки виртуального терминала.
     * для преобразования пользовательского ввода, полученного окном консоли, в последовательности виртуального терминала консоли, которые
     * может быть получен вспомогательным приложением с помощью функций ReadFile или ReadConsole.
     *
     * <br>
     * Типичное использование этого флага предполагается в сочетании с ENABLE_VIRTUAL_TERMINAL_PROCESSING на
     * дескриптор вывода для подключения к приложению
     * который общается исключительно через последовательности виртуальных терминалов.
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
