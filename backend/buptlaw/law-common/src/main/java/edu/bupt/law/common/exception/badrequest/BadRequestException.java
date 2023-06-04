package edu.bupt.law.common.exception.badrequest;

public abstract class BadRequestException extends RuntimeException{
    public BadRequestException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BadRequestException(String msg) {
        super(msg);
    }
}
