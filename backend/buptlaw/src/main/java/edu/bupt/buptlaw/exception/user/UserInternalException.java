package edu.bupt.buptlaw.exception.user;

public class UserInternalException extends UserException{
    public UserInternalException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserInternalException(String msg) {
        super(msg);
    }
}
