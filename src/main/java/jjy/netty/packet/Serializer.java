package jjy.netty.packet;

/**
 * @Auther: ${JJY}
 * @Date: 2019/9/10 14:38
 * @Description: 序列化
 */
public interface Serializer {

    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化
     * @param o
     * @return
     */
    byte [] serialize(Object o);

    /**
     * 反序列化
     * @param t
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> t,byte[] bytes);
}
