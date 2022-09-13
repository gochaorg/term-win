package xyz.cofe.term.win.test;

import groovy.console.ui.Console;
import groovy.lang.Closure;
import xyz.cofe.io.fn.IOFun;
import xyz.cofe.term.win.WinConsole;
import xyz.cofe.text.Text;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.regex.Pattern;

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

    private Console _groovyConsole;
    private Console groovyConsole(){
        if( _groovyConsole!=null )return _groovyConsole;
        synchronized (this){
            if( _groovyConsole!=null )return _groovyConsole;
            _groovyConsole = new Console();
            _groovyConsole.setVariable("con", winConsole);
            var logFn = new Closure(this,this){
                @Override
                public Object call() {
                    return null;
                }

                @Override
                public Object call(Object... args) {
                    logs(Arrays.toString(args));
                    return null;
                }

                @Override
                public Object call(Object arguments) {
                    if( arguments instanceof Iterable ){
                        StringBuilder sb = new StringBuilder();
                        for( var e : (Iterable)arguments ){
                            sb.append(e!=null ? e.toString():"null");
                        }
                        logs(sb.toString());
                    } else {
                        logs(arguments==null ? "null" : arguments.toString());
                    }
                    return null;
                }
            };
            _groovyConsole.setVariable("logs",logFn);
            _groovyConsole.run();
            return _groovyConsole;
        }
    }

    private void showGroovyConsole(){
        var cons = groovyConsole();
        windowOf(cons.getToolbar()).ifPresent( wnd -> {
            if( wnd instanceof JFrame ){
                ((JFrame) wnd).setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            }
            wnd.setVisible(true);
            wnd.toFront();
        });
    }

    private void showSampleGroovy(String code){
        if( code==null )throw new IllegalArgumentException("code==null");
        showGroovyConsole();
        var cons = groovyConsole();
        cons.getInputArea().setText(code);
    }

    private Optional<Window> windowOf(java.awt.Component cmpt){
        if( cmpt==null )return Optional.empty();
        if( cmpt instanceof Window )return Optional.of((Window) cmpt);

        var prnt = cmpt.getParent();
        if( prnt == null )return Optional.empty();

        return windowOf(prnt);
    }

    private void init(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

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

        menu(menuBar)
            .menu("ScreenBuffer", sb ->
                sb.action("info", ()->{
                    try {
                        logs(winConsole.getScreenBufferInfo().toString());
                    } catch (Throwable err){
                        logs(err.toString());
                    }})
            )
            .menu("ConsoleMode", mb -> {
                mb.action("info", ()->{
                    try {
                        logs(winConsole.getConsoleMode().toString());
                    } catch (Throwable err){
                        logs(err.toString());
                    }
                });
            })
            .menu("Groovy", mb -> {
                mb.action("show", this::showGroovyConsole);
                if( Samples.samples().size()>0 ){
                    mb.menu("samples", sampleMenuBuilder -> {
                        Samples.samples().forEach(sampleMenuBuilder::script);
                    });
                }
            })
        ;
    }

    //region MenuBuilder
    private MenuBuilder menu(JMenu menu){
        return new MenuBuilder(menu);
    }

    private MenuBarBuilder menu(JMenuBar menuBar){
        return new MenuBarBuilder(menuBar);
    }

    private class MenuBarBuilder {
        public final JMenuBar menuBar;
        public MenuBarBuilder(JMenuBar bar){
            menuBar = bar;
        }

        public MenuBarBuilder menu(String name, Consumer<MenuBuilder> builder) {
            JMenu menu = new JMenu(name);
            menuBar.add(menu);
            builder.accept(new MenuBuilder(menu));
            return this;
        }
    }

    private class MenuBuilder {
        public final JMenu menu;
        public MenuBuilder(JMenu menu){
            this.menu = menu;
        }

        public MenuBuilder action(String name, Runnable code){
            var mi = new JMenuItem(name);
            menu.add(mi);
            mi.addActionListener(ev -> {
                code.run();
            });
            return this;
        }

        public MenuBuilder script(String name, String code){
            var mi = new JMenuItem(name);
            menu.add(mi);
            mi.addActionListener(ev -> {
                showSampleGroovy(code);
            });
            return this;
        }

        public MenuBuilder menu(String name, Consumer<MenuBuilder> builder){
            var m = new JMenu(name);
            menu.add(m);
            builder.accept(new MenuBuilder(m));
            return this;
        }
    }
    //endregion

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
