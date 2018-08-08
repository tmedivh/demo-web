package com.api.exception;

/*
 * Copyright (C), 2011-2018 温州贷
 * Author: miaoyusong
 * Date:  2018/8/8 上午12:33
 * Description:
 */
public class WebException extends RuntimeException {

    public WebException() {
        super();
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebException(Throwable cause) {
        super(cause);
    }

    protected WebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
