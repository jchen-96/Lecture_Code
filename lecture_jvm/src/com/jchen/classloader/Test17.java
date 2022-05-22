package com.jchen.classloader;

public class Test17 {
    public static void main(String[] args) throws Exception{
        Test15 loader=new Test15("loader1");

        Class<?> c=loader.loadClass("com.jchen.classloader.Sample");

        System.out.println(c.hashCode());
//        注释掉该行，不会实例化这个对象，但是Test16可能被预加载了
//        Object o=c.newInstance();
    }
}
