package jjy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import jjy.netty.packet.Packet;
import jjy.netty.packet.PacketCodeC;

/**
 * @Auther: ${JJY}
 * @Date: 2019/9/16 17:49
 * @Description:
 */
public class PackEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.INSTACE().encode(byteBuf,packet);
    }
}
