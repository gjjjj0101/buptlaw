package edu.bupt.law.user.exception.handler;

import edu.bupt.law.common.exception.ExceptionResponseBody;
import edu.bupt.law.user.exception.UserBadRequestException;
import edu.bupt.law.user.exception.UserInternalException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponseBody handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new ExceptionResponseBody(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }


    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponseBody handleBadCredentialsException(BadCredentialsException e) {
        return new ExceptionResponseBody(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    @ExceptionHandler(UserInternalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponseBody handleUserInternalException(UserInternalException e) {
        return new ExceptionResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(UserBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseBody handleUserBadRequestException(UserBadRequestException e) {
        return new ExceptionResponseBody(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

}