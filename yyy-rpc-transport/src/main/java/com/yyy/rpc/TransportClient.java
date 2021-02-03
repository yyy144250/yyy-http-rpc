package com.yyy.rpc;

import model.Point;

import java.io.InputStream;

public interface TransportClient {
    void connect(Point point);
    InputStream write(InputStream data);
    void close();
}
