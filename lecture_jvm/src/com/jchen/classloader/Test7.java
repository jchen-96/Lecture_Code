package com.jchen.classloader;

public class Test7 {
    public static void main(String[] args) throws Exception{
        Class<?> c = Class.forName("java.lang.String");
        System.out.println(c.getClassLoader());
        Class<?> c1=Class.forName("com.jchen.classloader.C");
        System.out.println(c1.getClassLoader());
    }
}

class C {

}
