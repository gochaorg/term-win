package xyz.cofe.term.win;

import com.sun.jna.platform.win32.Wincon;

import java.util.ArrayList;

public class ScreenBufferInfo {
    private final int xCursor;
    public int getXCursor() {
        return xCursor;
    }

    private final int yCursor;
    public int getYCursor() {
        return yCursor;
    }

    private final int width;
    public int getWidth() {
        return width;
    }

    private final int height;
    public int getHeight() {
        return height;
    }

    private final int widthMax;
    public int getWidthMax() {
        return widthMax;
    }

    private final int heightMax;
    public int getHeightMax() {
        return heightMax;
    }

    private final CharAttributes attributes;
    public CharAttributes getCharAttributes(){ return attributes; }

    public ScreenBufferInfo(Wincon.CONSOLE_SCREEN_BUFFER_INFO info){
        if( info==null )throw new IllegalArgumentException("info==null");
        xCursor = info.dwCursorPosition.X;
        yCursor = info.dwCursorPosition.Y;
        width = info.dwSize.X;
        height = info.dwSize.Y;
        widthMax = info.dwMaximumWindowSize.X;
        heightMax = info.dwMaximumWindowSize.Y;
        attributes = new CharAttributes(info.wAttributes);
    }

    public String toString(){
        var sb = new StringBuilder();
        var ls = new ArrayList<String>();
        ls.add("xCursor="+xCursor);
        ls.add("yCursor="+yCursor);
        ls.add("width="+width);
        ls.add("height="+height);
        ls.add("widthMax="+widthMax);
        ls.add("heightMax="+heightMax);
        ls.add("attributes="+attributes);

        sb.append("ScreenBufferInfo{");
        sb.append(ls.stream().reduce("", (a,b)->a.length()>0 ? a+","+(b.length()>0 ? ","+b : "") : b));
        sb.append("}");
        return sb.toString();
    }
}
