package xyz.cofe.term.win;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Wincon;
import com.sun.jna.ptr.IntByReference;

public interface WinConsoleRawAPI extends Kernel32, Wincon {
    int GetConsoleTitleW(char[] lpConsoleTitle, int nSize); // TODO check jna char[] for wstring
    boolean SetConsoleTitleW(WString lpConsoleTitle); // TODO check jna char[] for wstring
    boolean ReadConsoleInputW(HANDLE hConsoleInput, INPUT_RECORD[] lpBuffer, int nLength, IntByReference lpNumberOfEventsRead);
    boolean PeekConsoleInputW(HANDLE hConsoleInput, INPUT_RECORD[] lpBuffer, int nLength, IntByReference lpNumberOfEventsRead);
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

    public boolean SetConsoleTextAttribute(HANDLE output, short attributes);

    public HANDLE CreateConsoleScreenBuffer(int dwDesiredAccess, int dwShareMode, LPVOID lpSecurityAttributes, int dwFlags, LPVOID lpScreenBufferData);

    public boolean SetConsoleActiveScreenBuffer(HANDLE outputHandle);

    public COORDbyValue GetLargestConsoleWindowSize(HANDLE outputHandle);

    public boolean FlushConsoleInputBuffer(HANDLE inputHandle);

    // https://learn.microsoft.com/en-us/windows/console/registering-a-control-handler-function
    // https://github.com/java-native-access/jna/blob/master/www/CallbacksAndClosures.md
    public interface ConsoleCtrlHandler extends StdCallCallback {
        boolean handle(int fdwCtrlType);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean SetConsoleCtrlHandler(ConsoleCtrlHandler handler, boolean add);

    public boolean SetConsoleScreenBufferSize(HANDLE outputHandle, COORDbyValue size);

    public boolean SetConsoleWindowInfo(HANDLE outputHandle, boolean absolute, SMALL_RECT rect);
}
