package com.jchen.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;



public class Test13 extends ClassLoader{
    private String classLoaderName;

    private String path;//指定class文件目录

    public void setPath(String path) {
        this.path = path;
    }

    private final String extensionName=".class";

    public Test13(String classLoaderName){
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


    public Test13(ClassLoader parent, String classLoaderName){
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
        Test13 loader=new Test13("loader");
//        这样写还是在classpath的路径下，还是appclassLoader加载的
//        loader.setPath("C:\\Users\\JC\\Desktop\\秋招\\Lecture_Code\\lecture_jvm\\out\\production\\cd lecture_jvm\\");
//        将其放到桌面上,并且要把classpath路径下的同名class文件删掉。！！！！！！！！！！！！！
        loader.setPath("C:\\Users\\JC\\Desktop\\");
        Class<?> c=loader.loadClass("com.jchen.classloader.Test1");


        System.out.println(c.hashCode());
        Object o=c.newInstance();
        System.out.println(o);
        System.out.println(c.getClassLoader());


        System.out.println("*************如果第二次加载*******");

//
        Test13 loader2=new Test13("loader2");
        loader2.setPath("C:\\Users\\JC\\Desktop\\");
        Class<?> c2=loader2.loadClass("com.jchen.classloader.Test1");
        System.out.println(c2.hashCode());
        System.out.println(c2.getClassLoader());
        o=c2.newInstance();
        System.out.println(o);

        //这两个class的hashCode不一样，是因为 命名空间，
        // 这两个类在不同的命名空间 loader1 和loader2构成不同的命名空间

        String bootstrapPath = System.getProperty("sun.boot.class.path");
        System.out.println("BootStrapClassLoader Path : " + bootstrapPath);
        String extPath = System.getProperty("java.ext.dirs");
        System.out.println("ExtClassLoader Path : " + extPath);
        String appPath = System.getProperty("java.class.path");
        System.out.println("AppClassLoader Path : " + appPath);


    }

}
