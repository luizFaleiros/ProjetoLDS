package br.com.projeto.LDS.exceptions.handler;

import br.com.projeto.LDS.exceptions.AuthorizationException;
import br.com.projeto.LDS.exceptions.DuplicateException;
import br.com.projeto.LDS.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(RuntimeException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(RuntimeException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<StandardError> duplicate(RuntimeException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.IM_USED.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.IM_USED).body(err);
    }

}
