package fr.clientui.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OuvrageBadRequestException extends RuntimeException{

    public OuvrageBadRequestException(String message) {
        super(message);
    }
}
