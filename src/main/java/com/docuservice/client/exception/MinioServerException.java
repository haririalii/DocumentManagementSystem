package com.docuservice.client.exception;

public class MinioServerException extends RuntimeException {

    public MinioServerException() {
    }

    public MinioServerException(String message) {
        super(message);
    }

    public MinioServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinioServerException(Throwable cause) {
        super(cause);
    }

    public MinioServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
