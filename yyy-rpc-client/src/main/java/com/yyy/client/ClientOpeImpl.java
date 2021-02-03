package com.yyy.client;

import com.yyy.rpc.TransportClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import model.Point;
import utils.reflect.ReflectUtils;

@Slf4j
public class ClientOpeImpl implements ClientOpe{
    private TransportClient client;
    @Override
    public synchronized void init(Point point, Class<? extends TransportClient> transportClient) {
        client= ReflectUtils.newInstance(transportClient);
        client.connect(point);
        log.info("连接server成功");
    }

    @Override
    public TransportClient container() {
        log.info("获取到client");
        return client;
    }

    @Override
    public synchronized void close() {
        client.close();
    }
}
