package model;

import lombok.Data;

@Data
public class Request {

    /*方法相关信息*/
    private ServiceInfo serviceInfo;

//    参数具体的值
    private Object[] parameter;

}
