package edu.bupt.buptlaw.exception.user;

public abstract class UserException extends RuntimeException {
    public UserException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserException(String msg) {
        super(msg);
    }
}
