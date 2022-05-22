package com.jchen.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

//并没有用到我们自己的类加载器，双亲委派机制

//定义类加载器，初始类加载器(返回那个类的加载器)


public class Test11 extends ClassLoader{
    private String classLoaderName;

    private final String extensionName=".class";

    public Test11(String classLoaderName){
        super();//将系统类加载器作为该加载器的父加载器
        this.classLoaderName=classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        根本没有执行！！！！！！！！！！！！！！！！！
        byte[] data=this.loadData(name);
        return this.defineClass(name,data,0,data.length);
    }
    private byte[] loadData(String name){
        InputStream is=null;
        byte[] data=null;

        ByteArrayOutputStream baos=null;

        try{
            is=new FileInputStream(new File(name+this.extensionName));
            baos=new ByteArrayOutputStream();
            int ch=0;
            while (-1!=(ch=is.read())){
                baos.write(ch);
            }
            data=baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }


    public Test11(ClassLoader parent, String classLoaderName){
        super(parent);//显式的指定该类加载器的父加载器。
        this.classLoaderName=classLoaderName;
    }

    @Override
    public String toString() {
        return "Test11{" +
                "classLoaderName='" + classLoaderName + '\'' +
                '}';
    }

    public static void test(ClassLoader classLoader)throws Exception{
        Class<?> c=classLoader.loadClass("com.jchen.classloader.Test1");
        Object o=c.newInstance();
        System.out.println(o);
        System.out.println(c.getClassLoader());
    }

    public static void main(String[] args) throws Exception{
        Test11 t=new Test11("loader");
        test(t);
    }

}
