package com.api.bean;

import io.swagger.annotations.ApiModelProperty;

public class ResponseVO {

    @ApiModelProperty(value = "返回码 200 成功")
    private int code;
    @ApiModelProperty(value = "返回信息(包含错误信息)")
    private String msg;
    @ApiModelProperty(value = "返回数据")
    private Object data;

    public ResponseVO(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
