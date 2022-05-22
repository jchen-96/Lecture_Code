package com.jchen.classloader;

public class Test16 {
    public Test16(){
        System.out.println("test16 is loaded by:"+this.getClass().getClassLoader());
    }

}
