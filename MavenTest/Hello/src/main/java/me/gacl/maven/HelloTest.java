package me.gacl.maven;
//����junit�İ�
import org.junit.Test;
import static junit.framework.Assert.*;

public class HelloTest {

    @Test
    public void testHello(){
        Hello hello = new Hello();
        String results = hello.sayHello("gacl");
        assertEquals("Hello gacl!",results);        
    }
}