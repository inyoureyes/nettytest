package jjy.netty.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @Auther: ${JJY}
 * @Date: 2019/9/10 14:47
 * @Description:
 */
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;

    private PacketCodeC (){};

    public static PacketCodeC INSTACE (){
        return new PacketCodeC();
    }

    /**
     * 编码
     * @param packet 数据包
     * @return
     */
    public ByteBuf encode(Packet packet){
       //创建ByteBuf对象
       ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

       byte[] bytes = Serializer.DEFAULT.serialize(packet);

       byteBuf.writeInt(MAGIC_NUMBER)
               .writeByte(packet.getVersion())
               .writeByte(Serializer.DEFAULT.getSerializerAlgorithm())
               .writeByte(packet.getConmmand())
               .writeInt(bytes.length)
               .writeBytes(bytes);

       return byteBuf;

    }

    public void encode(ByteBuf byteBuf,Packet packet){

        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        byteBuf.writeInt(MAGIC_NUMBER)
                .writeByte(packet.getVersion())
                .writeByte(Serializer.DEFAULT.getSerializerAlgorithm())
                .writeByte(packet.getConmmand())
                .writeInt(bytes.length)
                .writeBytes(bytes);
    }

    public Packet dencode(ByteBuf byteBuf){
        // 跳过magic_number
        byteBuf.skipBytes(4);

        //跳过版本号
        byteBuf.skipBytes(1);

        //获取序列化标识
        byte serializerAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        int length = byteBuf.readInt();

        byte [] bytes = new byte[length];

        byteBuf.readBytes(bytes);

        Class<? extends Packet> reqType = getRequestType(command);

        Serializer serializer = getSerializer(serializerAlgorithm);

        if(reqType != null && serializer != null){
           return serializer.deserialize(reqType,bytes);
        }
       return null;
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        int serializerAlgorithmInt = Byte.toUnsignedInt(serializerAlgorithm);

        if(serializerAlgorithmInt == 1 ){
            return Serializer.DEFAULT;
        }
        return null;
    }

    private Class<? extends Packet> getRequestType(byte command) {
        int commandInt = Byte.toUnsignedInt(command);

        if(commandInt == 1){
            return LoginRequestPacket.class;
        }else if(commandInt == 2){
            return LoginReponsePacket.class;
        }else if(commandInt == 3){
            return MessageRequestPacket.class;
        }else if(commandInt == 4){
            return MessageResponsePacket.class;
        }
        return null;
    }

}
