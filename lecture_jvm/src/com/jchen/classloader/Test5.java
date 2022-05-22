package com.jchen.classloader;

//当一个接口初始化的时候，并不要求其父接口都初始化。

//只有真正使用到父接口的时候(例如引用接口中定义的常量)才会初始化。
public class Test5 {
    public static void main(String[] args) {
        System.out.println(Child5.b);
    }
}
interface Parent5{
    public static final int a=5;
}
interface Child5 extends Parent5{
//    接口里面的成员变量的默认值就是public static final
    public static final int b=6;
}
