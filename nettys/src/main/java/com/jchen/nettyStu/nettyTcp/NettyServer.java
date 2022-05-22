package com.jchen.nettyStu.nettyTcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws Exception {
        //创建bossgroup和workgroup
        EventLoopGroup bossgroup = new NioEventLoopGroup();//处理连接请求
        EventLoopGroup workgroup = new NioEventLoopGroup();//业务处理
        try {


            //服务器端启动参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossgroup, workgroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道测试对象
                        //给pipline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("server is ready");
            ChannelFuture cf = bootstrap.bind(6668).sync();//启动服务器

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();//对关闭通道进行监听

        }finally {
            bossgroup.shutdownGracefully();
            workgroup.shutdownGracefully();
        }
    }
}
