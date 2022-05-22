package com.jchen.memory;


//使用jconsole 检测死锁


public class DeadLock {
    Object t1=new Object();
    Object t2=new Object();

    Thread thread=new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                synchronized (t1) {
                    synchronized (t2) {
                        System.out.println("11111");
                    }
                }
            }
        }
    });
    Thread thread2=new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                synchronized (t2) {
                    synchronized (t1) {
                        System.out.println("222222222222222");
                    }
                }
            }
        }
    });

    public static void main(String[] args) {
        DeadLock d=new DeadLock();
        d.thread.start();
        d.thread2.start();
    }



}
