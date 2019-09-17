package jjy.netty.packet;

import lombok.Data;
import lombok.NoArgsConstructor;

import static jjy.netty.packet.Command.MESSAGE_RESPONSE;

@NoArgsConstructor
@Data
public class MessageResponsePacket extends Packet {

   private String message;

   public MessageResponsePacket(String message){
       this.message = message;
   }

    @Override
    public Byte getConmmand() {
        return MESSAGE_RESPONSE;
    }
}
