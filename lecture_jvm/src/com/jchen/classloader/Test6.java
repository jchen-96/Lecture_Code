package com.jchen.classloader;

//这个示例就很有意思了，很显然的表明了，准备阶段的赋初值，和初始化阶段的从上到下初始化
// (类加载的连接的第三个阶段)。
//准备阶段的重要意义！！！

public class Test6 {
    public static void main(String[] args) {
        Singleton singleton=Singleton.getInstance();
        System.out.println(Singleton.counter);
        System.out.println(Singleton.counter2);

    }
}
class Singleton{
    public static int counter;


    public static Singleton singleton=new Singleton();

    private Singleton(){
        counter++;
        counter2++;
        System.out.println("construct:"+counter);
        System.out.println("construct:"+counter2);
    }

    public static int counter2=0;

    public static Singleton getInstance(){
        return singleton;
    }
}
