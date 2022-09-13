package xyz.cofe.term.win;

import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Wincon;
import com.sun.jna.ptr.IntByReference;

public interface WinConsoleRawAPI extends Kernel32, Wincon {
    int GetConsoleTitleW(char[] lpConsoleTitle, int nSize);
    boolean SetConsoleTitleW(String lpConsoleTitle);
    boolean ReadConsoleInputW(HANDLE hConsoleInput, INPUT_RECORD[] lpBuffer, int nLength, IntByReference lpNumberOfEventsRead);
    boolean WriteConsoleW(HANDLE hConsoleOutput, WString lpBuffer, int nNumberOfCharsToWrite, IntByReference lpNumberOfCharsWritten, LPVOID lpReserved);

    @Structure.FieldOrder({ "X", "Y" })
    public static class COORDbyValue extends Structure implements Structure.ByValue {
        public short X;
        public short Y;

        @Override
        public String toString() {
            return String.format("COORD(%s,%s)", X, Y);
        }
    }
    boolean SetConsoleCursorPosition(HANDLE hConsoleOutput, COORDbyValue cursorPosition);

    // https://docs.microsoft.com/en-us/windows/console/console-cursor-info-str
    @Structure.FieldOrder({ "size", "visible" })
    public static class CURSOR_INFO extends Structure {
        public int size;
        public boolean visible;
        public static class ByValue extends CURSOR_INFO implements Structure.ByValue {}
    }

    public boolean SetConsoleCursorInfo(HANDLE output, CURSOR_INFO info);

    public boolean GetConsoleCursorInfo(HANDLE output, CURSOR_INFO info);
}
