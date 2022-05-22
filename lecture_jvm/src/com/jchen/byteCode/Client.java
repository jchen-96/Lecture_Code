package com.jchen.byteCode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Calendar;

public class Client {
    public static void main(String[] args) {

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");


        RealSubject rs=new RealSubject();
        Class<?> cs=rs.getClass();


        InvocationHandler dp=new DynamicSubject(rs);


        Subject subject=(Subject) Proxy.newProxyInstance(cs.getClassLoader(),cs.getInterfaces(),dp);

        subject.request();

        System.out.println(subject.getClass());
        System.out.println(subject.getClass().getSuperclass());
    }
}
