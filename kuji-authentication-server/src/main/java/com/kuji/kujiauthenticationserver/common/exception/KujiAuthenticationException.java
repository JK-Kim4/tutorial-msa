package com.kuji.kujiauthenticationserver.common.exception;

public class KujiAuthenticationException extends RuntimeException {


    public KujiAuthenticationException() {
        super();
    }

    public KujiAuthenticationException(String message) {
        super(message);
    }

    public KujiAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public KujiAuthenticationException(Throwable cause) {
        super(cause);
    }

    protected KujiAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
