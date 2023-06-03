package edu.bupt.law.common.exception.internal;

public abstract class InternalException extends RuntimeException{
    public InternalException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InternalException(String msg) {
        super(msg);
    }
}
