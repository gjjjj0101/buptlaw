package edu.bupt.law.user.exception.handler;

import edu.bupt.law.common.exception.ExceptionResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponseBody handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new ExceptionResponseBody(404, e.getMessage());
    }

}