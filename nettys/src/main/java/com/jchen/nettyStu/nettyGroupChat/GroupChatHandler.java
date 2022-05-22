package com.jchen.nettyStu.nettyGroupChat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

public class GroupChatHandler extends SimpleChannelInboundHandler<String> {

    //定义一个channel组
    private static ChannelGroup channels=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);//单例模式的全局器件

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //一旦连接第一个被执行
    //将当前channel 添加到channels
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        //将该客户加入聊天的信息推送给其他在线的客户端
        channels.writeAndFlush("[客户端]"+channel.remoteAddress()+"加入聊天"+sdf.format(new java.util.Date())+"\n");
        channels.add(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"离线了");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        channels.writeAndFlush("[客户端]"+channel.remoteAddress()+"离开了\n");
        System.out.println("channels size:"+channels.size());
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel=ctx.channel();

        //发送给别人消息
        channels.forEach(ch->{
            if(channel!=ch){
                ch.writeAndFlush("[客户]"+channel.remoteAddress()+"发送消息"+msg+"\n");
            }else{
                ch.writeAndFlush("自己发送了消息:"+msg+"\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
