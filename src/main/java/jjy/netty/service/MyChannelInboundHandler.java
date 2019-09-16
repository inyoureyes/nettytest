package jjy.netty.service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import jjy.netty.packet.LoginReponsePacket;
import jjy.netty.packet.LoginRequestPacket;
import jjy.netty.packet.Packet;
import jjy.netty.packet.PacketCodeC;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Scanner;

/**
 * @Auther: ${JJY}
 * @Date: 2019/6/28 17:47
 * @Description:
 */
public class MyChannelInboundHandler extends SimpleChannelInboundHandler<ByteBuf> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
//        System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
//        Scanner scanner = new Scanner(System.in);
//        String msg = scanner.nextLine();
//        ByteBuf out =  getByteBuf(channelHandlerContext,msg);
//        channelHandlerContext.writeAndFlush(out);

        Packet packet = PacketCodeC.INSTACE().dencode(byteBuf);
        LoginReponsePacket loginReponsePacket = new LoginReponsePacket();
        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            if(voliate(loginRequestPacket)){
                System.out.println("用户："+loginRequestPacket.getUsername()+"登陆成功!");
                loginReponsePacket.setSuccess(true);
            }else{
                System.out.println("用户："+loginRequestPacket.getUsername()+"登陆失败!");
                loginReponsePacket.setErrorCode(500);
                loginReponsePacket.setErrorMsg("密码校验失败!");

            }
        }
        ByteBuf responseByteBuf = PacketCodeC.INSTACE().encode(loginReponsePacket);
        channelHandlerContext.writeAndFlush(responseByteBuf);
    }

    private boolean voliate(LoginRequestPacket loginRequestPacket) {

        return false;
    }

    /**
     * 已连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ByteBuf out = getByteBuf(ctx,"欢迎连接！");
        ctx.writeAndFlush(out);

    }

    private ByteBuf getByteBuf(ChannelHandlerContext context,String msg){
        byte [] msgByte = msg.getBytes(Charset.forName("utf-8"));
        ByteBuf byteBuf = context.alloc().buffer();
        byteBuf.writeBytes(msgByte);
        return byteBuf;
    }
}
