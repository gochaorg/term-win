package xyz.cofe.term.win;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Wincon;
import com.sun.jna.ptr.IntByReference;

public interface WinConsoleRawAPI extends Kernel32, Wincon {
    int GetConsoleTitleW(char[] lpConsoleTitle, int nSize);
    boolean SetConsoleTitleW(String lpConsoleTitle);

    boolean ReadConsoleInputW(HANDLE hConsoleInput, INPUT_RECORD[] lpBuffer, int nLength, IntByReference lpNumberOfEventsRead);
}
