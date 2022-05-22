package com.jchen.nettyStu.groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GServer {
    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final int port=6667;


    public GServer() {
        try{
            selector=Selector.open();
            listenChannel=ServerSocketChannel.open();

            listenChannel.socket().bind(new InetSocketAddress(port));

            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //监听
    public void listen(){
        try {
            while (true){
                int count=selector.select(20000);
                if(count>0){
                    Iterator<SelectionKey> iterator=selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key=iterator.next();
                        if(key.isAcceptable()){
                            SocketChannel socketChannel=listenChannel.accept();
                            socketChannel.configureBlocking(false);

                            socketChannel.register(selector,SelectionKey.OP_READ);

                            System.out.println(socketChannel.getRemoteAddress()+"上线");
                        }
                        if(key.isReadable()){
                            readData(key);
                        }
                        //当前key 删除
                        iterator.remove();
                    }
                }else{
                    System.out.println("等待。。。。。");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    private void readData(SelectionKey key) throws Exception{
        SocketChannel sc=null;
        try {
            sc= (SocketChannel) key.channel();
            ByteBuffer buffer= ByteBuffer.allocate(1024);

            int count=sc.read(buffer);

            if(count>0){
                String msg = new String(buffer.array());
                System.out.println("from 客户端"+msg);
                //向其他客户端转发消息
                sendInfo(msg,sc);

            }


        }catch (Exception e){
            try {
                System.out.println(sc.getRemoteAddress()+"离线了..");
                //取消注册
                key.cancel();
                sc.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    private void sendInfo(String msg,SocketChannel self) throws Exception{
        System.out.println("转发消息");
        for(SelectionKey key:selector.keys()){
            Channel channel=key.channel();
            if(channel instanceof SocketChannel&&channel!=self){
                SocketChannel dest=(SocketChannel)channel;
                ByteBuffer buffer=ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }
    }
    public static void main(String[] args) {

        //创建服务器对象
        GServer groupChatServer = new GServer();
        groupChatServer.listen();
    }
}
