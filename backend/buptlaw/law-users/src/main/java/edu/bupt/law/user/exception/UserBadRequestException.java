package edu.bupt.law.user.exception;

import edu.bupt.law.common.exception.badrequest.BadRequestException;

public class UserBadRequestException extends BadRequestException {
    public UserBadRequestException(String msg) {
        super(msg);
    }
}
