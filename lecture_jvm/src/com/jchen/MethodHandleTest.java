package com.jchen;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

public class MethodHandleTest {
    static class ClassA{
        public void println(String s){
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        Object obj=System.currentTimeMillis()%2==0?System.out:new ClassA();


    }

    private static MethodHandle getPrintlnMH(Object receiver) throws Throwable{
        MethodType mt=MethodType.methodType(void.class,String.class);
        return null;
        
    }
}
