package com.jchen;

public class Main {
    public static void main(String[] args) {
        Father f=new Son();
        System.out.println(f.a);
    }
}
class Father{
    public int a=1;
    public int b=3;
}
class Son extends Father{
    public int a=2;
}
