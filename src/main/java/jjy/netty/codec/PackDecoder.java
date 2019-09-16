package jjy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import jjy.netty.packet.PacketCodeC;

import java.util.List;

/**
 * @Auther: ${JJY}
 * @Date: 2019/9/16 17:41
 * @Description:
 */
public class PackDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCodeC.INSTACE().dencode(byteBuf));
    }
}
