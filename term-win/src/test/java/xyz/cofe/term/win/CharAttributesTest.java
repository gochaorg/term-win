package xyz.cofe.term.win;

import org.junit.jupiter.api.Test;

public class CharAttributesTest {
    @Test
    public void test01(){
        var attr = new CharAttributes(7);
        System.out.println(attr);

        assert !attr.isBgGreen();
    }
}
