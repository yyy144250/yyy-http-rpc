package com.yyy;

import com.yyy.server.YYYServer;

public class Server {
    public static void main(String[] args) {
        YYYServer server=new YYYServer();
        server.register(TestClass.class,new TestClassImpl());
        server.start();
    }
}
