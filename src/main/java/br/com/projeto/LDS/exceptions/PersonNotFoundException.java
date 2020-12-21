package br.com.projeto.LDS.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException() {
        super("Pessoa não encontrada");
    }

    public PersonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
