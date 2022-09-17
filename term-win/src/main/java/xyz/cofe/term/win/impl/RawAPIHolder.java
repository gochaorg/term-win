package xyz.cofe.term.win.impl;

import com.sun.jna.Native;
import xyz.cofe.term.win.WinConsoleRawAPI;

public class RawAPIHolder {
    private static volatile WinConsoleRawAPI rawAPIinstance;
    public static WinConsoleRawAPI rawAPI(){
        if( rawAPIinstance!=null )return rawAPIinstance;
        synchronized (RawAPIHolder.class){
            if( rawAPIinstance!=null )return rawAPIinstance;
            rawAPIinstance = Native.load("Kernel32", WinConsoleRawAPI.class);
            return rawAPIinstance;
        }
    }
}
