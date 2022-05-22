package com.jchen.byteCode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {
    private Object subject;

    public DynamicSubject(Object o){
        this.subject=o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before calling");
        method.invoke(this.subject,args);
        System.out.println("after calling");

        return null;
    }
}
