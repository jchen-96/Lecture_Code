package com.jchen.garbage;

public class CMSTest {
    public static void main(String[] args) {
        int size=1024*1024;

        byte[] allocate1=new byte[4*size];
        System.out.println("1111111");
        byte[] allocate2=new byte[4*size];
        System.out.println("222222222");
        byte[] allocate3=new byte[4*size];
        System.out.println("33333333");
        byte[] allocate4=new byte[2*size];
        System.out.println("444444444");
    }
}
