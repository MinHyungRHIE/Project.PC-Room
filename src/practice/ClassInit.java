package practice;

import java.lang.reflect.Constructor;

public class ClassInit {
    public static void main(String[] args) throws Exception{
        Class<?> c = Class.forName("practice.test");
        Constructor<?> init = c.getDeclaredConstructor(String.class);
        test t = (test)init.newInstance("문자너짐");
        //test t = new test("문자너짐");
        System.out.println(t);
    }
}
