package jjy.netty.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import jjy.netty.packet.MessageRequestPacket;
import jjy.netty.packet.MessageResponsePacket;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(new Date()+"服务端接收消息:【"+messageRequestPacket.getMessage()+"】");
        messageResponsePacket.setMessage("服务端回复消息【"+messageRequestPacket.getMessage()+"】");
        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }
}
