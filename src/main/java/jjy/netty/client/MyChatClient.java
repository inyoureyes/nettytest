package jjy.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Auther: ${JJY}
 * @Date: 2019/6/28 17:52
 * @Description:
 */
public class MyChatClient {

    public static void main(String[] args) {
        Bootstrap bootstrap  = new Bootstrap();
        EventLoopGroup executors = new NioEventLoopGroup();
        try {
            bootstrap.group(executors)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress("127.0.0.1",8080))
                    .handler(new MyChannelInitializerImpl());
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executors.shutdownGracefully();
        }
    }
}
