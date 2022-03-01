package com.tiposCambio.cambio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IllegalArgEx extends IllegalArgumentException {
    public IllegalArgEx(String message){super(message);}
}
