package xyz.cofe.term.win.test;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            var frame = new JFrame("hello");
            frame.setSize(200,200);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
