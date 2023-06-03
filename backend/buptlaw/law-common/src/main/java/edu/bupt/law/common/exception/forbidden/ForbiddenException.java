package edu.bupt.law.common.exception.forbidden;

public abstract class ForbiddenException extends RuntimeException{
    public ForbiddenException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ForbiddenException(String msg) {
        super(msg);
    }

}
