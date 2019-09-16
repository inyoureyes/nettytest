package jjy.netty.packet;

import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: ${JJY}
 * @Date: 2019/9/10 14:41
 * @Description:
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object o) {
        return JSONObject.toJSONBytes(o);
    }

    @Override
    public <T> T deserialize(Class<T> t, byte[] bytes) {
        return JSONObject.parseObject(bytes,t);
    }
}
