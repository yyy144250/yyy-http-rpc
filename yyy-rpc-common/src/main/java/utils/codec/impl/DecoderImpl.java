package utils.codec.impl;

import com.alibaba.fastjson.JSON;
import utils.codec.Decoder;
/*
* json字符转换为类对象的工具类
* */
public class DecoderImpl implements Decoder {

    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
