package com.jchen.classloader;


//对于静态字段来说，只有直接定义了该字段的类会被初始化(类加载的第三个阶段)
// (通过下面的参数，可以看到类都被加载了，只是没有被初始化)


//当一个类在初始化的时候，要求所有父类都被初始化。
//在这种情况下，子类是否被加载了，可以通过这个参数 -XX:+TraceClassLoading 用于追踪类的加载信息并打印出来


public class Test1 {
    public static void main(String[] args) {
        System.out.println(Child.str);
    }
}

class Parent{
    public static String str="parent str";
    static {
        System.out.println("parent staic block");
    }
}
class Child extends  Parent{

//    这个不会输出
    static {
        System.out.println("children static block");
    }
}
