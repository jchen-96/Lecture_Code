package com.jchen.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

//类的卸载


public class Test15 extends ClassLoader{
    private String classLoaderName;

    private String path;//指定class文件目录

    public void setPath(String path) {
        this.path = path;
    }

    private final String extensionName=".class";

    public Test15(String classLoaderName){
        super();//将系统类加载器作为该加载器的父加载器
        this.classLoaderName=classLoaderName;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        根本没有执行！！！！！！！！！！！！！！！！！
        System.out.println("find class invoke");
        byte[] data=this.loadData(name);
        return this.defineClass(name,data,0,data.length);
    }
    private byte[] loadData(String name){
        InputStream is=null;
        byte[] data=null;
        ByteArrayOutputStream baos=null;

        name=name.replace(".","\\");

        try{
            is=new FileInputStream(new File(this.path+name+this.extensionName));
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


    public Test15(ClassLoader parent, String classLoaderName){
        super(parent);//显式的指定该类加载器的父加载器。
        this.classLoaderName=classLoaderName;
    }

    @Override
    public String toString() {
        return "Test11{" +
                "classLoaderName='" + classLoaderName + '\'' +
                '}';
    }



    public static void main(String[] args) throws Exception{
        Test15 loader=new Test15("loader");
        loader.setPath("C:\\Users\\JC\\Desktop\\");
        Class<?> c=loader.loadClass("com.jchen.classloader.Test1");
        System.out.println(c.hashCode());
        Object o=c.newInstance();
        System.out.println(o);
        System.out.println(c.getClassLoader());

        loader=null;
        c=null;
        o=null;

        System.gc();
        Thread.sleep(200000);//jvisual 可以查看类的卸载

        loader=new Test15("loader");
        loader.setPath("C:\\Users\\JC\\Desktop\\");
        c=loader.loadClass("com.jchen.classloader.Test1");
        System.out.println(c.hashCode());
        o=c.newInstance();
        System.out.println(o);
        System.out.println(c.getClassLoader());


    }

}
