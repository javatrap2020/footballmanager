package com.javatrap2020.footballmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TeamIdException extends RuntimeException {
    public TeamIdException(String message) {
        super(message);
    }
}
