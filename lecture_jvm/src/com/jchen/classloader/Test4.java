package com.jchen.classloader;

public class Test4 {
    public static void main(String[] args) {
//        并不会对这个类主动使用。
//        new 出来的类型是虚拟机在运行期生成的。[Lcom.jchen.classloader.Parent4
//        创建的类型的父类是Object
        Parent4[] parent4s=new Parent4[1];
        System.out.println(parent4s.getClass());
        System.out.println(parent4s.getClass().getSuperclass());

    }
}
class Parent4{
    static {
        System.out.println("parent4 static block");
    }
}
