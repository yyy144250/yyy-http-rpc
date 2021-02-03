package com.yyy.server;

import com.yyy.rpc.RequestHandler;
import com.yyy.rpc.TransportServer;
import lombok.extern.slf4j.Slf4j;
import model.Request;
import model.Response;
import model.ServiceInfo;
import sun.misc.IOUtils;
import utils.codec.Decoder;
import utils.codec.Encoder;
import utils.reflect.ReflectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@Slf4j
public class YYYServer {
    private TransportServer server;
    private ServiceConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public YYYServer(ServiceConfig serviceConfig){
        this.config=serviceConfig;
        this.server= ReflectUtils.newInstance(config.getTransportClass());
        this.encoder= ReflectUtils.newInstance(config.getEncoder());
        this.decoder=ReflectUtils.newInstance(config.getDecoder());
        this.server.init(config.getPort(),this.myHandler);
        this.serviceInvoker=new ServiceInvoker();
        this.serviceManager=new ServiceManager();
    }
    public YYYServer(){
        this(new ServiceConfig());
    }
    public void start(){
        this.server.start();
    }
    public void stop(){
        this.server.stop();
    }
    public <T> void register(Class<T> interfaceClass,T bean){
        serviceManager.register(interfaceClass,bean);
    }
    private RequestHandler myHandler=new RequestHandler() {
        @Override
        public void resHandler(InputStream req, OutputStream res) {
            Response response=new Response();
            try {
                byte[] reqByte= IOUtils.readFully(req,req.available(),true);
                Request request=decoder.decode(reqByte,Request.class);
                log.info("get request:{}",request);

                ServiceModel findResult = serviceManager.find(request);
                Object result = serviceInvoker.serviceInvoke(findResult, request);
                response.setData(result);
            } catch (Exception e) {
                response.setMsg("RPCServer get error:"+e.getClass().getName()
                        +":"+e.getMessage());
                response.setCode(1);
                log.error(e.getMessage());
            }finally {
                byte[] resp= encoder.encode(response);
                try {
                    res.write(resp);
                    log.info("已作出响应");
                } catch (IOException e) {
                    log.error("响应失败");
                }
            }
        }
    };
}
