package com.jchen.classloader;

import java.util.UUID;

public class Test3 {
    public static void main(String[] args) {
        System.out.println(Parent3.str);
    }
}
class Parent3{
    public static final String str= UUID.randomUUID().toString();

    //和test2 不同的是，这里能输出static block
    //对于编译期能确定的常量，才会不打印输出这个staic block
    //对于编译器不能确定的常量，在程序运行的时候，会导致主动使用这个常量所在的类，显然会导致这个类的初始化。
    static {
        System.out.println("parent3 static block");
    }
}