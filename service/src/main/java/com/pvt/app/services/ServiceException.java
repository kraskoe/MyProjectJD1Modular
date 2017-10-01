package com.pvt.app.services;

/**
 * Created by ykrasko on 15/08/2017.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
