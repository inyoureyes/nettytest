package jjy.netty.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import jjy.netty.codec.PackDecoder;
import jjy.netty.codec.PackEncoder;

import javax.net.ssl.SSLEngine;

/**
 * @Auther: ${JJY}
 * @Date: 2019/8/27 15:15
 * @Description:
 */
public class ServiceChanelInitializer extends ChannelInitializer<Channel> {


    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                .addLast(new PackDecoder())
                .addLast(new LoginRequestHandler())
                .addLast(new MyChannelInboundHandler())
                .addLast(new PackEncoder());
    }
}
