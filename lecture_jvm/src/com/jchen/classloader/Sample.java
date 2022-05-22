package com.jchen.classloader;

public class Sample {
    public Sample(){
        System.out.println("sample is loaded by:"+this.getClass().getClassLoader());
        new Test16();
    }
}
