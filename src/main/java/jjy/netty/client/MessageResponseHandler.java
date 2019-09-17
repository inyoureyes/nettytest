package jjy.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import jjy.netty.packet.MessageRequestPacket;
import jjy.netty.packet.MessageResponsePacket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
        System.out.println(new Date()+"客户端接收消息【"+messageResponsePacket.getMessage()+"】");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        messageRequestPacket.setMessage("客户端发送消息【"+line+"】");
        channelHandlerContext.channel().writeAndFlush(messageRequestPacket);
    }
}
