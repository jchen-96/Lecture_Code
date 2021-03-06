package com.jchen.nettyStu.nettyGroupChat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class GroupChatClient {
    private final String host;

    private final  int port;

    public GroupChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public void run()throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {


            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            pipeline.addLast(new GroupChatClientHandler());

                        }
                    });

            ChannelFuture sync = bootstrap.connect(host, port).sync();
            Channel channel=sync.channel();
            System.out.println("---------------"+channel.localAddress()+"is ready");

            Scanner scanner=new Scanner(System.in);

            while (scanner.hasNextLine()){
                String ms=scanner.nextLine();
                channel.writeAndFlush(ms+"\r\n");
            }
        }finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception{
        new GroupChatClient("127.0.0.1",8080).run();
    }
}
