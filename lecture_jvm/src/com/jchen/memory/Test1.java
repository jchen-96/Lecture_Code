package com.jchen.memory;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) throws Exception{
        List<Test1> list=new ArrayList<>();
        while (true){
            list.add(new Test1());
            Thread.sleep(0,10);
            System.gc();//这里竟然会把那些类丢弃掉？
        }
     }
}
