package xyz.cofe.term.win.test;

import org.junit.jupiter.api.Test;

public class SamplesTest {
    @Test
    public void read(){
        Samples.samples().forEach( (name,code) -> {
            System.out.println("name: "+name);
            System.out.println(code);
            System.out.println();
        } );
    }
}
