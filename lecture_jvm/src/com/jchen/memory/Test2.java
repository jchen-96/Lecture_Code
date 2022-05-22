package com.jchen.memory;

public class Test2 {
    private int length;

    public void test()throws Exception{
        length++;
        Thread.sleep(300);
        test();
    }

    public static void main(String[] args)throws Exception {
        Test2 t=new Test2();
        t.test();
    }
}
