package xyz.cofe.term.win.test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        var scan = new Scanner(System.in);
        var out = System.out;
        out.println("type \"exit\" - for exit");
        while (true) {
            out.print("hello> ");
            out.flush();

            var line = scan.nextLine();
            if( line.trim().equalsIgnoreCase("exit") )break;

            out.println("you enter "+line.trim());
        }
    }
}
