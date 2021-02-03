package model;

import lombok.Data;

@Data
public class Response {
    /*状态码*/
    private int code=0;

    /*返回的信息*/
    private String msg="OK";

    /*返回的数据*/
    private Object data;
}
