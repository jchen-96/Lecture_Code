package com.jchen.nettyStu.nettyTcp;

import io.netty.util.NettyRuntime;

public class Test {
    public static void main(String[] args) {

        System.out.println(NettyRuntime.availableProcessors() * 2);
    }
}
