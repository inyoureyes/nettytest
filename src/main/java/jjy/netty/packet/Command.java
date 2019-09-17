package jjy.netty.packet;

/**
 * @Auther: ${JJY}
 * @Date: 2019/9/10 14:35
 * @Description:
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    byte LOGIN_REPONSE = 2;

    byte MESSAGE_REQUEST = 3;

    byte MESSAGE_RESPONSE = 4;
}
