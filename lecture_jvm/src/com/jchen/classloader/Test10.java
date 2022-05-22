package com.jchen.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class Test10 {
    public static void main(String[] args) throws IOException {
        ClassLoader loader=Thread.currentThread().getContextClassLoader();

        String resourceName="com/jchen/classloader/Test10.class";

        Enumeration<URL> urls=loader.getResources(resourceName);

        while (urls.hasMoreElements()){
            URL url=urls.nextElement();
            System.out.println(url);
        }
    }
}
