package xyz.cofe.term.win.test;

import xyz.cofe.term.win.WinConsole;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        init();
    }

    public MainFrame(String title) throws HeadlessException {
        super(title);
        init();
    }

    private final JTextPane logTextPane = new JTextPane();
    private final JScrollPane logScrollPane = new JScrollPane(logTextPane);
    private final WinConsole winConsole = new WinConsole();

    private boolean autoScroll = true;

    private void init(){
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(logScrollPane);
        timer.setRepeats(true);
        timer.setDelay(500);
        timer.setInitialDelay(1000);
        timer.start();

        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout());
        getContentPane().add(lowerPanel, BorderLayout.SOUTH);

        JCheckBox scrollToggle = new JCheckBox("Scroll");
        scrollToggle.setSelected(autoScroll);
        lowerPanel.add(scrollToggle);
        scrollToggle.addChangeListener(ev -> {
            var autoScroll0 = scrollToggle.isSelected();
            if( autoScroll0!=autoScroll ) {
                autoScroll = autoScroll0;
                log("auto scroll set " + (autoScroll ? "on" : "off") + "\n");
            }
        });

        JButton clear = new JButton("Clear");
        lowerPanel.add(clear);
        clear.addActionListener(ev->{
            logTextPane.setText("");
        });
    }

    private final Timer timer = new Timer(500,ev -> {
        pollMessageQueue();
        if( autoScroll )scroll2end();
        readConsoleInput();
    });

    private void readConsoleInput(){
        try {
            winConsole.readInput().forEach(inputEvent -> {
                log(inputEvent.toString() + "\n");
            });
        } catch (Throwable err){
            log("error: "+err+"\n");
        }
    }

    //region logging
    private void scroll2end(){
        var max = logScrollPane.getVerticalScrollBar().getMaximum();
        var curVal = logScrollPane.getVerticalScrollBar().getValue();
        if( curVal<max ){
            logScrollPane.getVerticalScrollBar().setValue(max);
        }
    }

    private void write2log(String message){
        if( message==null )return;
        try {
            logTextPane.getStyledDocument().insertString(logTextPane.getStyledDocument().getLength(),message,null);
        } catch (BadLocationException e) {
            setTitle(e.getClass().getSimpleName()+" "+e.getMessage());
            System.err.println(e);
        }
    }

    private void pollMessageQueue(){
        while (true){
            var msg = messageQueue.poll();
            if( msg==null )break;
            write2log(msg);
        }
    }

    private final java.util.Queue<String> messageQueue = new ConcurrentLinkedQueue<>();

    private void log(String message){
        if(message==null)throw new IllegalArgumentException("message==null");
        messageQueue.add(message);
    }

    private void logs(String message){
        if(message==null)throw new IllegalArgumentException("message==null");
        messageQueue.add(message+"\n");
    }
    //endregion

    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            var frame = new MainFrame("hello");
            frame.setSize(500,300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
