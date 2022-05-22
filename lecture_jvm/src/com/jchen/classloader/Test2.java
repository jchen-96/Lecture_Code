package com.jchen.classloader;

//

public class Test2 {
    public static void main(String[] args) {
        System.out.println(Parent2.str);
    }
}
class Parent2{
//    加了final之后，就变成了常量，static block就不会被初始化
//    对于一个常量，常量在编译阶段会被放入调用这个常量的方法所在的类的常量池中，
//    本质上调用类并没有直接引用到定义常量的类，因此并不会触发定义常量的类的初始化。


//    (对于本case，也就是在Test2的常量池中！！！！
//    ！！！！！之后parent就没有任何关系了，甚至我们可以将parent的class 删除)。
    public static String str="myparent2";

    static {
        System.out.println("myparent2 static block");
    }
}