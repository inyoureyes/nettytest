package jjy.netty.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import jjy.netty.packet.LoginReponsePacket;
import jjy.netty.packet.LoginRequestPacket;

/**
 * @Auther: ${JJY}
 * @Date: 2019/9/16 17:45
 * @Description:
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println("----发现客户端登录----");
        LoginReponsePacket loginReponsePacket = new LoginReponsePacket();
        if(voliate(loginRequestPacket)){
            System.out.println("用户："+loginRequestPacket.getUsername()+"登陆成功!");
            loginReponsePacket.setSuccess(true);
        }else{
            System.out.println("用户："+loginRequestPacket.getUsername()+"登陆失败!");
            loginReponsePacket.setErrorCode(500);
            loginReponsePacket.setErrorMsg("密码校验失败!");

        }
        channelHandlerContext.channel().writeAndFlush(loginReponsePacket);
    }
    private boolean voliate(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
