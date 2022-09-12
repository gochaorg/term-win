package xyz.cofe.term.win;

import com.sun.jna.platform.win32.Wincon;

public class ScreenBufferInfo {
    public final int xCursor;
    public final int yCursor;

    public final int width;
    public final int height;

    public final int widthMax;
    public final int heightMax;

    public ScreenBufferInfo(Wincon.CONSOLE_SCREEN_BUFFER_INFO info){
        if( info==null )throw new IllegalArgumentException("info==null");
        xCursor = info.dwCursorPosition.X;
        yCursor = info.dwCursorPosition.Y;
        width = info.dwSize.X;
        height = info.dwSize.Y;
        widthMax = info.dwMaximumWindowSize.X;
        heightMax = info.dwMaximumWindowSize.Y;
    }
}
