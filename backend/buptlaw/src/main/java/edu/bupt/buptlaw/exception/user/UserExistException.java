package edu.bupt.buptlaw.exception.user;

public class UserExistException extends UserException{
    public UserExistException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserExistException(String msg) {
        super(msg);
    }
}
