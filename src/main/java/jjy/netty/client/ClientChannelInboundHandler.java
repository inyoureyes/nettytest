package jjy.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import jjy.netty.packet.LoginReponsePacket;
import jjy.netty.packet.LoginRequestPacket;
import jjy.netty.packet.Packet;
import jjy.netty.packet.PacketCodeC;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;


/**
 * @Auther: ${JJY}
 * @Date: 2019/6/28 17:58
 * @Description:
 */
public class ClientChannelInboundHandler implements ChannelInboundHandler {

    private ByteBuf getByteBuf(ChannelHandlerContext context,String msg){
        byte [] msgByte = msg.getBytes(Charset.forName("utf-8"));
        ByteBuf byteBuf = context.alloc().buffer();
        byteBuf.writeBytes(msgByte);
        return byteBuf;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println(new Date()+"------客户端开始登陆-----");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("jijingyao");
        loginRequestPacket.setPassword("a123456");

        ByteBuf byteBuf = PacketCodeC.INSTACE().encode(loginRequestPacket);
        channelHandlerContext.writeAndFlush(byteBuf);
    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

        Packet packet = PacketCodeC.INSTACE().dencode((ByteBuf) o);

        if(packet instanceof LoginReponsePacket){
            LoginReponsePacket loginReponsePacket = (LoginReponsePacket) packet;
            if(loginReponsePacket.isSuccess()){
                System.out.println("您已登陆成功!");
            }else {
                System.out.println("登陆失败!code："+loginReponsePacket.getErrorCode()+",errorMsg:"+loginReponsePacket.getErrorMsg());
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

    }
}
