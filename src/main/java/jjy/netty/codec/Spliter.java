package jjy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import jjy.netty.packet.Packet;
import jjy.netty.packet.PacketCodeC;

import static jjy.netty.packet.PacketCodeC.MAGIC_NUMBER;

public class Spliter extends LengthFieldBasedFrameDecoder {

    private final static Integer LENGTH_FIELD_OFFSET = 7;
    private final static Integer LENGTH_FIELD_LENGTH = 4;


    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if(in.getInt(in.readerIndex()) != MAGIC_NUMBER){
            ctx.channel().close();
            return  null;
        }
        return super.decode(ctx, in);
    }
}
