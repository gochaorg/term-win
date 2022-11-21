package xyz.cofe.term.win;

import com.sun.jna.platform.win32.Kernel32Util;
import com.sun.jna.platform.win32.WinNT;

import java.util.List;
import java.util.Optional;

import static xyz.cofe.term.win.WinConsoleError.throwError;
import static xyz.cofe.term.win.impl.RawAPIHolder.rawAPI;

/**
 * Представляет набор функций для работы с консолью
 *
 * <p>
 * Основаня документация по функцию консоли есть по этой
 * <a href="https://docs.microsoft.com/en-us/windows/console/using-the-high-level-input-and-output-functions">ссылке</a>
 * на docs.microsoft.com
 *
 * <h2>Запуск консольного приложения</h2>
 *
 * Что бы использовать данный класс, java приложение следует запустить с использованием javaw.exe.
 * В обычном использовании java.exe консольнуые функции не доступны по причине архитекуры ОС.
 *
 * <p>
 * При первом создании экземпляра WinConsole, будет создана консоль {@code rawAPI().AllocConsole()} ({@link WinConsoleRawAPI#AllocConsole()})
 *
 * <br>
 * Когда JVM будет завершать работу ({@code Runtime.getRuntime().addShutdownHook}), консоль будет освобождена {@link WinConsoleRawAPI#FreeConsole()}
 *
 * <h2>Ввод-вывод</h2>
 *
 * С консолью связаны обычно 3 стандарных канала
 * <ul>
 *     <li>stdOutputHandle : {@link WinNT.HANDLE} - std output, канал вывода обычной информации в файл/терминал</li>
 *     <li>stdErrorHandle : {@link WinNT.HANDLE} -  std errput, канал вывода ошибок информации в файл/терминал</li>
 *     <li>stdInputHandle : {@link WinNT.HANDLE} -  std input,  канал ввода из файла/клавиатуры/.... </li>
 * </ul>
 */
public class WinConsole {
    //region stdOutputHandle, stdInputHandle, stdErrorHandle
    private static volatile WinNT.HANDLE stdOutputHandle;

    /**
     * Возвращает handle для stdout
     * @return handle для stdout
     */
    public static WinNT.HANDLE getStdOutputHandle(){
        if( stdOutputHandle !=null )return stdOutputHandle;
        synchronized (WinConsole.class){
            if( stdOutputHandle !=null )return stdOutputHandle;
            var handle = rawAPI().GetStdHandle(WinConsoleRawAPI.STD_OUTPUT_HANDLE);
            if( WinConsoleRawAPI.INVALID_HANDLE_VALUE.equals(handle) ){
                throwError("GetStdHandle(STD_OUTPUT_HANDLE) return INVALID_HANDLE_VALUE");
            }
            stdOutputHandle = handle;
            return stdOutputHandle;
        }
    }

    private static volatile WinNT.HANDLE stdInputHandle;

    /**
     * Возвращает handle для stdinput
     * @return handle для stdinput
     */
    public static WinNT.HANDLE getStdInputHandle(){
        if( stdInputHandle!=null )return stdInputHandle;
        synchronized (WinConsole.class){
            if( stdInputHandle!=null )return stdInputHandle;
            var handle = rawAPI().GetStdHandle(WinConsoleRawAPI.STD_INPUT_HANDLE);
            if( WinConsoleRawAPI.INVALID_HANDLE_VALUE.equals(handle) ){
                throwError("GetStdHandle(STD_INPUT_HANDLE) return INVALID_HANDLE_VALUE");
            }
            stdInputHandle = handle;
            return stdInputHandle;
        }
    }

    private static volatile WinNT.HANDLE stdErrorHandle;

    /**
     * Возвращает handle для std error
     * @return handle для std error
     */
    public static WinNT.HANDLE getStdErrorHandle(){
        if( stdErrorHandle!=null )return stdErrorHandle;
        synchronized (WinConsole.class){
            if( stdErrorHandle!=null )return stdErrorHandle;
            var handle = rawAPI().GetStdHandle(WinConsoleRawAPI.STD_ERROR_HANDLE);
            if( WinConsoleRawAPI.INVALID_HANDLE_VALUE.equals(handle) ){
                throwError("GetStdHandle(STD_ERROR_HANDLE) return INVALID_HANDLE_VALUE");
            }
            stdErrorHandle = handle;
            return stdErrorHandle;
        }
    }
    //endregion

    private static volatile boolean allocated;

    private final WinNT.HANDLE outputHandle;
    private final WinConsoleOutput output;
    private final WinNT.HANDLE inputHandle;
    private final WinConsoleInput input;

    private final WinNT.HANDLE errorHandle;
    private final WinConsoleOutput errput;

    public WinConsole(){
        synchronized (WinConsole.class){
            if( !allocated ){
                if( !rawAPI().AllocConsole() ){
                    throwError("AllocConsole");
                }
                allocated = true;
                Runtime.getRuntime().addShutdownHook(new Thread(()->{
                    if( !rawAPI().FreeConsole() ){
                        var errno = rawAPI().GetLastError();
                        var errmsg = Kernel32Util.getLastErrorMessage();
                        System.err.println("FreeConsole fail: "+errno+" "+errmsg);
                    }
                }));
            }
        }

        outputHandle = getStdOutputHandle();
        output = new WinConsoleOutput(outputHandle);

        inputHandle = getStdInputHandle();
        input = new WinConsoleInput(inputHandle);

        errorHandle = getStdErrorHandle();
        errput = new WinConsoleOutput(errorHandle);
    }

    /**
     * Консоль для вывода в stdout, в целом можно писать и в stderr - разницы нет
     * @return консоль вывода в stdout
     */
    @Deprecated(since = "будет удалено в следующем major")
    public WinConsoleOutput getOutput(){ return output; }

    /**
     * Консоль для вывода в stderr, в целом можно писать и в stdout - разницы нет
     * @return консоль вывода в stdout
     */
    @Deprecated(since = "будет удалено в следующем major")
    public WinConsoleOutput getErrput(){ return errput; }

    /**
     * Консоль для чтения событий ввода
     * @return чтение событий ввода
     */
    public WinConsoleInput getInput(){ return input; }

    //region availableInputEventsCount : int

    /**
     * Возвращает кол-во полученных, но не прочетанных событий ввода
     * @return кол-во событий ввода в буфере
     */
    public int getAvailableInputEventsCount() {
        return input.getAvailableInputEventsCount();
    }
    //endregion
    //region inputMode : InputMode

    /**
     * Возвращает режим ввода
     * @return режим ввода
     */
    public InputMode getInputMode() {
        return input.getInputMode();
    }

    /**
     * Указывает режим ввода
     * @param mode режим ввода
     */
    public void setInputMode(InputMode mode) {
        input.setInputMode(mode);
    }
    //endregion
    //region inputCodePage : CodePage

    /**
     * Возвращает текущую кодовую страницу
     * @return кодовая страница
     */
    public Optional<CodePage> getInputCodePageOptional() {
        return input.getCodePageOptional();
    }

    /**
     * Указывает текущую кодовую страницу
     * @param codePage кодовая страница
     */
    public void setInputCodePage(CodePage codePage) {
        input.setCodePage(codePage);
    }
    //endregion
    //region read(eventCount):List<InputEvent>

    /**
     * Читает события из буфера, потенциально - блокируемая операция, лучше предварительно проверить наличие данных через {@link #getAvailableInputEventsCount()}
     * @param eventCount сколько событий прочесть и удалить из буфера
     * @return события
     */
    public List<InputEvent> read(int eventCount) {
        return input.read(eventCount);
    }
    //endregion
    //region read():List<InputEvent>

    /**
     * Читает события из буфера, потенциально - блокируемая операция, лучше предварительно проверить наличие данных через {@link #getAvailableInputEventsCount()}
     * @return события
     */
    public List<InputEvent> read() {
        return input.read();
    }
    //endregion
    //region peek(eventCount):List<InputEvent>

    /**
     * Читает события из буфера, не удаляя их - блокировать не должно
     * @param eventCount сколько событий ожидаем
     * @return события ввода
     */
    public List<InputEvent> peek(int eventCount) {
        return input.peek(eventCount);
    }
    //endregion
    //region flush()

    /**
     * Очистить буфер ввода
     */
    public void flushInput() {
        input.flush();
    }
    //endregion

    //region screenBufferMode : ScreenBufferMode
    public ScreenBufferMode getScreenBufferMode() {
        return output.getScreenBufferMode();
    }

    public void setScreenBufferMode(ScreenBufferMode mode) {
        output.setScreenBufferMode(mode);
    }
    //endregion
    //region outputCodePage : CodePage
    public Optional<CodePage> getOutputCodePageOptional() {
        return output.getCodePageOptional();
    }

    public void setOutputCodePage(CodePage codePage) {
        output.setCodePage(codePage);
    }
    //endregion
    //region cursorInfo : CursorInfo
    public CursorInfo getCursorInfo() {
        return output.getCursorInfo();
    }

    public void setCursorInfo(CursorInfo cursorInfo) {
        output.setCursorInfo(cursorInfo);
    }
    //endregion

    //region screenBufferInfo : ScreenBufferInfo
    public ScreenBufferInfo getScreenBufferInfo() {
        return output.getScreenBufferInfo();
    }
    //endregion
    //region charAttributes : CharAttributes
    public CharAttributes getCharAttributes() {
        return output.getCharAttributes();
    }

    public void setCharAttributes(CharAttributes attributes) {
        if( attributes==null )throw new IllegalArgumentException("attributes==null");
        output.setCharAttributes(attributes);
    }
    //endregion

    public int write(String text) {
        if( text==null )throw new IllegalArgumentException("text==null");
        return output.write(text);
    }

    public void cursor(int x, int y) {
        output.cursor(x, y);
    }

    //region largestSize : LargestSize
    public LargestSize getLargestSize() {
        return output.getLargestSize();
    }
    //endregion
    //region title : String
    public String getTitle(){
        return WinConsoleCommon.getTitle();
    }
    public void setTitle(String title){
        if( title==null )throw new IllegalArgumentException("title==null");
        WinConsoleCommon.setTitle(title);
    }
    //endregion

    public ControlHolder controlHandle(ControlHandler handler){
        if( handler==null )throw new IllegalArgumentException("handler==null");
        return WinConsoleCommon.handleControl(handler);
    }

    public void controlHandleReset(){
        WinConsoleCommon.restoreControl();
    }
}
