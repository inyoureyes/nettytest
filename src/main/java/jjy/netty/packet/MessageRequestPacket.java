package jjy.netty.packet;

import lombok.Data;
import lombok.NoArgsConstructor;

import static jjy.netty.packet.Command.MESSAGE_REQUEST;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket (String message){
        this.message = message;
    }

    @Override
    public Byte getConmmand() {

        return MESSAGE_REQUEST;
    }
}
