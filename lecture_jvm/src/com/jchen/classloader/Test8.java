package com.jchen.classloader;

//手动加载类不会进行静态变量初始化

public class Test8 {
    public static void main(String[] args) throws Exception{
        ClassLoader loader=ClassLoader.getSystemClassLoader();
        Class<?> c1=loader.loadClass("com.jchen.classloader.CL");
        System.out.println(c1);
        System.out.println("**********");
        c1=Class.forName("com.jchen.classloader.CL");
        System.out.println(c1);
    }
}
class CL{
    static {
        System.out.println("CL static block");
    }
}
