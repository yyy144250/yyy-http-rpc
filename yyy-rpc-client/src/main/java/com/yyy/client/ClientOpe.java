package com.yyy.client;

import com.yyy.rpc.TransportClient;
import model.Point;

public interface ClientOpe {
    void init(Point point, Class<? extends TransportClient> transportClient);
    TransportClient container();
    void close();
}
