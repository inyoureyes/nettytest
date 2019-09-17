package jjy.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import jjy.netty.packet.LoginReponsePacket;
import jjy.netty.packet.LoginRequestPacket;
import jjy.netty.packet.MessageRequestPacket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginReponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginReponsePacket loginReponsePacket) throws Exception {
        if(loginReponsePacket.isSuccess()){
            System.out.println("客户端登录成功!");
            MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            messageRequestPacket.setMessage("客户端发送消息【"+line+"】");
            channelHandlerContext.channel().writeAndFlush(messageRequestPacket);
        }else{
            System.out.println("登录失败,原因:"+loginReponsePacket.getErrorMsg());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date()+"------客户端开始登陆-----");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("jijingyao");
        loginRequestPacket.setPassword("a123456");
        ctx.channel().writeAndFlush(loginRequestPacket);
    }
}
