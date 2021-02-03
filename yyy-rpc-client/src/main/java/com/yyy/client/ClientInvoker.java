package com.yyy.client;

import com.yyy.rpc.TransportClient;
import lombok.extern.slf4j.Slf4j;
import model.Request;
import model.Response;
import model.ServiceInfo;
import org.apache.commons.io.IOUtils;
import utils.codec.Decoder;
import utils.codec.Encoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/*
* 动态代理需要实现的类
*
* */
@Slf4j
public class ClientInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private ClientOpe clientOpe;

    public ClientInvoker(Class clazz, Encoder encoder, Decoder decoder, ClientOpe clientOpe) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.clientOpe = clientOpe;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request=new Request();
        request.setServiceInfo(ServiceInfo.getKey(clazz,method));
        request.setParameter(args);
//        System.out.println("这是request"+request);
        Response response=reqHandler(request);
        if (response==null||response.getCode()!=0){
            throw new IllegalStateException("invoke发生了错误"+response);
        }
        return response.getData();
    }
/*
* 处理请求，发送请求并得到响应结果
* */
    private Response reqHandler(Request request) {
        Response res=null;
        TransportClient client=null;

        try {
            client=clientOpe.container();
            byte[] reqByte= encoder.encode(request);
            InputStream write = client.write(new ByteArrayInputStream(reqByte));
            byte[] resByte= IOUtils.readFully(write,write.available());
            res=decoder.decode(resByte,Response.class);
//            System.out.println("这是res"+res);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            res=new Response();
            res.setCode(1);
            res.setMsg("rpc got error:"+e.getMessage()+e.getClass());
            throw new IllegalStateException(e);
        }
        finally {
//            if(client!=null){
//                client.close();
//            }
        }

        return res;
    }
}
