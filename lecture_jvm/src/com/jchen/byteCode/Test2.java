package com.jchen.byteCode;

public class Test2 implements i1,i2{
    String str="hello world";

    private int i=1;

    public static void main(String[] args) {
        System.out.println("test");
    }
    public synchronized static void test() throws InterruptedException{
        Thread.sleep(1000);
    }

    public void syTest(String s) throws InterruptedException{
        synchronized (s){
            System.out.println("hello world");
            Thread.sleep(100);
        }
    }
}
interface i1{}
interface i2{}
