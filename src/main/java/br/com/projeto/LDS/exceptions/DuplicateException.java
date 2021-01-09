package br.com.projeto.LDS.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.IM_USED)
public class DuplicateException extends RuntimeException {
    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateException(String message) {
        super(message);
    }

}
