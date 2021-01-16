package br.com.projeto.LDS.exceptions.handler;

import br.com.projeto.LDS.exceptions.AuthorizationException;
import br.com.projeto.LDS.exceptions.DuplicateException;
import br.com.projeto.LDS.exceptions.FileException;
import br.com.projeto.LDS.exceptions.NotContentException;
import br.com.projeto.LDS.exceptions.NotFoundException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
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

    @ExceptionHandler(NotContentException.class)
    public ResponseEntity<StandardError> noContent(RuntimeException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.NO_CONTENT.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(err);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> file(RuntimeException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
        HttpStatus code = HttpStatus.valueOf(e.getStatusCode());
        StandardError err = new StandardError(code.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(code).body(err);
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {
        HttpStatus code = HttpStatus.valueOf(e.getStatusCode());
        StandardError err = new StandardError(code.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(code).body(err);
    }


}
