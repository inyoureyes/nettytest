package jjy.netty.packet;

import lombok.Data;

import static jjy.netty.packet.Command.LOGIN_REQUEST;

/**
 * @Auther: ${JJY}
 * @Date: 2019/9/10 14:36
 * @Description:
 */
@Data
public class LoginRequestPacket extends  Packet {


    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getConmmand() {
        return LOGIN_REQUEST;
    }

}
