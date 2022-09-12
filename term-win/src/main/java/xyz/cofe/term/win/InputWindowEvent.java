package xyz.cofe.term.win;

import com.sun.jna.platform.win32.Wincon;

import java.util.ArrayList;

public class InputWindowEvent implements InputEvent {
    public InputWindowEvent(Wincon.WINDOW_BUFFER_SIZE_RECORD windowBufferSizeRecord){
        if( windowBufferSizeRecord==null )throw new IllegalArgumentException("windowBufferSizeRecord==null");
        width = windowBufferSizeRecord.dwSize.X;
        height = windowBufferSizeRecord.dwSize.Y;
    }

    private final int width;
    public int getWidth() {
        return width;
    }

    private final int height;

    public int getHeight() {
        return height;
    }

    public String toString(){
        var sb = new StringBuilder();
        var ls = new ArrayList<String>();
        ls.add("width="+width);
        ls.add("height="+height);

        sb.append("InputWindowEvent{");
        sb.append(ls.stream().reduce("", (a,b)->a.length()>0 ? a+","+(b.length()>0 ? ","+b : "") : b));
        sb.append("}");
        return sb.toString();
    }
}
