package com.yyy.client;

import utils.codec.Decoder;
import utils.codec.Encoder;
import utils.reflect.ReflectUtils;

import java.lang.reflect.Proxy;

public class YYYClient {
    private ClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private ClientOpe clientOpe;

    public YYYClient(ClientConfig config) {
        this.config = config;
        this.encoder= ReflectUtils.newInstance(config.getEncoder());
        this.decoder=ReflectUtils.newInstance(config.getDecoder());
        this.clientOpe=ReflectUtils.newInstance(config.getClientOpe());
        clientOpe.init(config.getPoint(), config.getTransportClient());
    }
    /*
    * 如果不需要改变配置就使用默认的配置
    * */
    public YYYClient(){
        this(new ClientConfig());
    }

    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new ClientInvoker(clazz,encoder,decoder,clientOpe)
        );
    }
}
