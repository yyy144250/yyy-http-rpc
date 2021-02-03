package com.yyy;

import com.yyy.client.YYYClient;

public class Client {
    public static void main(String[] args) {
        YYYClient client=new YYYClient();
        TestClass service=client.getProxy(TestClass.class);
        int add = service.add(1, 2);
        System.out.println(add);
    }

}
