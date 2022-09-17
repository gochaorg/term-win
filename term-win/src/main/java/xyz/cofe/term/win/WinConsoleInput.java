package xyz.cofe.term.win;

import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.Wincon;
import com.sun.jna.ptr.IntByReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static xyz.cofe.term.win.WinConsoleError.throwError;
import static xyz.cofe.term.win.impl.RawAPIHolder.rawAPI;

/**
 * Функции для работы с Input
 */
public class WinConsoleInput {
    private final WinNT.HANDLE inputHandle;
    public WinConsoleInput(WinNT.HANDLE inputHandle){
        if( inputHandle==null )throw new IllegalArgumentException("inputHandle==null");
        this.inputHandle = inputHandle;
    }

    public WinNT.HANDLE getHandle(){ return inputHandle; }

    //region availableInputEventsCount
    public int getAvailableInputEventsCount(){
        var cnt = new IntByReference();
        if( !rawAPI().GetNumberOfConsoleInputEvents(inputHandle,cnt) ){
            throwError("GetNumberOfConsoleInputEvents(inputHandle,cnt)");
        }
        return cnt.getValue();
    }
    //endregion
    //region inputMode
    public InputMode getInputMode(){
        IntByReference inputMode = new IntByReference();
        if( !rawAPI().GetConsoleMode(inputHandle,inputMode) ){
            throwError("GetConsoleMode(inputHandle)");
        }

        return new InputMode(inputMode.getValue());
    }
    public void setInputMode(InputMode mode){
        if( mode==null )throw new IllegalArgumentException("mode==null");
        if (!rawAPI().SetConsoleMode(inputHandle, mode.getInputFlags())) {
            throwError("SetConsoleMode(inputHandle," + mode.getInputFlags() + ")");
        }
    }
    //endregion
    //region codePage
    public Optional<CodePage> getCodePageOptional(){
        int code = rawAPI().GetConsoleCP();
        if( code==0 ){
            throwError("GetConsoleCP");
        }
        return Arrays.stream(CodePage.values()).sequential().filter(cp -> cp.code == code).findFirst();
    }
    public void setCodePage(CodePage codePage){
        if( codePage==null )throw new IllegalArgumentException("codePage==null");
        if( !rawAPI().SetConsoleCP(codePage.code) ){
            throwError("SetConsoleCP("+codePage.code+")");
        }
    }
    //endregion

    public List<InputEvent> read(int eventCount){
        if( eventCount<0 )throw new IllegalArgumentException("eventCount<0");
        if( eventCount==0 )return List.of();

        var inputRecords = new Wincon.INPUT_RECORD[eventCount];
        var readed = new IntByReference();
        if( !rawAPI().ReadConsoleInputW(inputHandle,inputRecords,inputRecords.length,readed) ){
            throwError("ReadConsoleInput(inputHandle,inputRecords,inputRecords.length,readed)");
        }

        var cnt = readed.getValue();
        if( cnt<=0 )return List.of();

        var events = new ArrayList<InputEvent>();
        for( var i=0; i<cnt; i++ ){
            InputEvent.read(inputRecords[i]).ifPresent(events::add);
        }
        return events;
    }

    public List<InputEvent> read(){
        return read(getAvailableInputEventsCount());
    }

    public List<InputEvent> peek(int eventCount){
        if( eventCount<0 )throw new IllegalArgumentException("eventCount<0");
        if( eventCount==0 )return List.of();

        var inputRecords = new Wincon.INPUT_RECORD[eventCount];
        var readed = new IntByReference();
        if( !rawAPI().PeekConsoleInputW(inputHandle,inputRecords,inputRecords.length,readed) ){
            throwError("PeekConsoleInputW(inputHandle,inputRecords,inputRecords.length,readed)");
        }

        var cnt = readed.getValue();
        if( cnt<=0 )return List.of();

        var events = new ArrayList<InputEvent>();
        for( var i=0; i<cnt; i++ ){
            InputEvent.read(inputRecords[i]).ifPresent(events::add);
        }
        return events;
    }

    public void flush(){
        if( !rawAPI().FlushConsoleInputBuffer(inputHandle) )
            throwError("FlushConsoleInputBuffer(inputHandle)");
    }
}
