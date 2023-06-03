package edu.bupt.law.common.exception.unauthorized;

public abstract class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UnauthorizedException(String msg) {
        super(msg);
    }

}
