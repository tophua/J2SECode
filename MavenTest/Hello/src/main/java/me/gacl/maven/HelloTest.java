package me.gacl.maven;
//导入junit的包
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