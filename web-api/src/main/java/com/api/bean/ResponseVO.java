package com.api.bean;

import io.swagger.annotations.ApiModelProperty;

public class ResponseVO {
    private static final long serialVersionUID = -8145865776285690954L;
    public static final int DEFAULT_STATUS_CODE = 200;
    private static final String DEFAULT_MESSAGE = "success";

    @ApiModelProperty(value = "返回码 200 成功")
    private int code;
    @ApiModelProperty(value = "返回信息(包含错误信息)")
    private String msg;
    @ApiModelProperty(value = "返回数据")
    private Object data;

    public ResponseVO() {
    }

    private ResponseVO(int code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseVOBuilder response() {
        return new ResponseVOBuilder();
    }

    public static ResponseVOBuilder response(int status_code, String message) {
        return new ResponseVOBuilder(status_code, message);
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

    public static class ResponseVOBuilder {
        private int code = DEFAULT_STATUS_CODE;
        private String msg = DEFAULT_MESSAGE;
        private Object data;

        public ResponseVOBuilder() {
            super();
        }

        public ResponseVOBuilder(int code, String msg) {
            super();
            this.code = code;
            this.msg = msg;
        }

        public ResponseVOBuilder setCode(int code) {
            this.code = code;
            return this;
        }

        public ResponseVOBuilder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public ResponseVOBuilder setData(Object data) {
            this.data = data;
            return this;
        }

        public ResponseVO build() {
            return new ResponseVO(code, msg, data);
        }
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
