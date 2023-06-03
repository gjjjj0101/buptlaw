package edu.bupt.law.common.exception.conflict;

public abstract class ConflictException extends RuntimeException{
    public ConflictException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ConflictException(String msg) {
        super(msg);
    }
}
