package edu.bupt.buptlaw.exception.handler;

import edu.bupt.buptlaw.exception.ExceptionResponseBody;
import edu.bupt.buptlaw.exception.user.UserException;
import edu.bupt.buptlaw.exception.user.UserExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {UserExistException.class})
    public ExceptionResponseBody handleUserExistException(HttpServletRequest request, UserExistException e){
        return new ExceptionResponseBody(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<?> handleUsernameNotFoundException(HttpServletRequest request, UsernameNotFoundException e){
        ExceptionResponseBody exceptionResponseBody = new ExceptionResponseBody();
        return ResponseEntity.badRequest().body( new ExceptionResponseBody());
    }
}
