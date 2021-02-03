package utils.codec.impl;

import com.alibaba.fastjson.JSON;
import utils.codec.Encoder;
/*
* 对象转换成json字符的工具类
* */
public class EncoderImpl implements Encoder {
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
