package xyz.cofe.term.win.test;

import xyz.cofe.term.win.WinConsole;

public class Test01 {
    public static void main(String[] args){
        System.out.println("create console");

        var console = new WinConsole();
        System.out.println("console title: "+console.getTitle());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("exit");
    }
}
