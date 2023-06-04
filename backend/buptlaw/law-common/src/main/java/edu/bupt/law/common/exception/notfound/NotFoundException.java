package edu.bupt.law.common.exception.notfound;

public abstract class NotFoundException extends RuntimeException{
    public NotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NotFoundException(String msg) {
        super(msg);
    }

}
