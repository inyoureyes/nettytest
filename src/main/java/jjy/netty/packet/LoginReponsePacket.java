package jjy.netty.packet;

import lombok.Data;

import static jjy.netty.packet.Command.LOGIN_REPONSE;

/**
 * @Auther: ${JJY}
 * @Date: 2019/9/10 15:28
 * @Description:
 */
@Data
public class LoginReponsePacket extends Packet{

    private boolean success;

    private Integer errorCode;

    private String errorMsg;

    @Override
    public Byte getConmmand() {
        return LOGIN_REPONSE;
    }
}
