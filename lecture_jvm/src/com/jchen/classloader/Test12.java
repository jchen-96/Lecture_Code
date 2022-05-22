package com.jchen.classloader;

import javax.swing.plaf.IconUIResource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


//使用自定义的类加载器，同时怎样不让系统加载器加载这个类。

public class Test12 extends ClassLoader{
    private String classLoaderName;

    private String path;//指定class文件目录

    public void setPath(String path) {
        this.path = path;
    }

    private final String extensionName=".class";

    public Test12(String classLoaderName){
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


    public Test12(ClassLoader parent, String classLoaderName){
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
        Test12 loader=new Test12("loader");
//        这样写还是在classpath的路径下，还是appclassLoader加载的
//        loader.setPath("C:\\Users\\JC\\Desktop\\秋招\\Lecture_Code\\lecture_jvm\\out\\production\\cd lecture_jvm\\");
//        将其放到桌面上,并且要把classpath路径下的同名class文件删掉。！！！！！！！！！！！！！
        loader.setPath("C:\\Users\\JC\\Desktop\\");
        Class<?> c=loader.loadClass("com.jchen.classloader.Test1");


        System.out.println(c.hashCode());
        Object o=c.newInstance();
        System.out.println(o);
        System.out.println(c.getClassLoader());


    }

}
