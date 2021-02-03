package com.yyy.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/*
* 在map里存的服务的最小单位
* 即反射所需要的的 object和method
* */
@Data
@AllArgsConstructor
public class ServiceModel {
    private Object target;
    private Method method;
}
