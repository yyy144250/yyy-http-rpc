package com.yyy.server;

import model.Request;
import utils.reflect.ReflectUtils;

/*
* service上调用
* */
public class ServiceInvoker {
    public Object serviceInvoke(ServiceModel serviceModel, Request request){
        return ReflectUtils.invoke(
                serviceModel.getTarget(),
                serviceModel.getMethod(),
                request.getParameter()
        );
    }
}
