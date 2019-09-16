package jjy.netty.packet;

import lombok.Data;

/**
 * @Auther: ${JJY}
 * @Date: 2019/9/10 14:32
 * @Description:
 */
@Data
public abstract class Packet {

    private Byte version = 1;

    public abstract Byte getConmmand();
}
