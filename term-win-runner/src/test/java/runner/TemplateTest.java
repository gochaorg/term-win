package runner;

import org.junit.jupiter.api.Test;

public class TemplateTest {
    @Test
    public void test01(){
        var tmpl = new Template("hello << >>JAVA_EXE>>GGG");
        tmpl.setJavaExe("javaexe123");
    }
}
