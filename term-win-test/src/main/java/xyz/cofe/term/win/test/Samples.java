package xyz.cofe.term.win.test;

import xyz.cofe.io.fn.IOFun;
import xyz.cofe.text.Text;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Samples {
    private static Map<String,String> samples;
    static {
        samples = new LinkedHashMap<>();
        var samplesUrl = MainFrame.class.getResource("/samples.groovy");
        if( samplesUrl!=null ){
            try {
                var lines = Text.splitNewLines(IOFun.readText(samplesUrl, StandardCharsets.UTF_8));
                var header = Pattern.compile("///+\\s+(?<title>[^/]+)/+");

                String currentTitle = null;
                StringBuilder content = new StringBuilder();

                for( var line : lines ){
                    var m = header.matcher(line);
                    if( m.matches() ){
                        var title = m.group("title").trim();
                        if( title.length()>0 ){
                            if( content.length()>0 && currentTitle!=null ){
                                samples.put(currentTitle,content.toString());
                            }
                            currentTitle = title;
                            content.setLength(0);
                        }
                    }else{
                        content.append(line).append(System.lineSeparator());
                    }
                }

                if( content.length()>0 && currentTitle!=null ){
                    samples.put(currentTitle,content.toString());
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public static Map<String,String> samples(){
        return Collections.unmodifiableMap(samples);
    }
}
