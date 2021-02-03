package com.yyy.server;

import lombok.extern.slf4j.Slf4j;
import model.Request;
import model.ServiceInfo;
import utils.reflect.ReflectUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ServiceManager {
    /*用来存放具体方法的容器*/
    private Map<ServiceInfo,ServiceModel> stubList;

    public ServiceManager() {
        this.stubList=new ConcurrentHashMap<>();
    }

    /*向容器中添加方法*/
    public<T> void register(Class<T> interfaceClass,T instance){
        Method[] methods= ReflectUtils.getPublicMethod(interfaceClass);
//        log.info("已经没问题");
        for(Method m:methods){
            ServiceModel serviceModel=new ServiceModel(instance,m);
            ServiceInfo serviceInfo=ServiceInfo.getKey(interfaceClass,m);
            stubList.put(serviceInfo,serviceModel);
            log.info("向容器中注册了方法：{}+{}",serviceInfo.getMethod(),serviceInfo.getClazz());
        }
    }

    /*查找方法*/
    public ServiceModel find(Request request){
        System.out.println("这是req"+request);
        ServiceInfo serviceInfo=request.getServiceInfo();
        log.info("查找到了方法",request.getServiceInfo().getMethod());
        return stubList.get(serviceInfo);
    }
}
