package jjy.netty.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import jjy.netty.codec.PackDecoder;
import jjy.netty.codec.PackEncoder;

/**
 * @Auther: ${JJY}
 * @Date: 2019/6/28 17:56
 * @Description:
 */
final public class MyChannelInitializerImpl extends ChannelInitializer<Channel> {

    protected void initChannel(Channel channel) throws Exception {

        channel.pipeline()
                .addLast(new PackDecoder())
                .addLast(new ClientChannelInboundHandler())
                .addLast(new PackEncoder());
    }
}
