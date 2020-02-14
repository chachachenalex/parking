package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CarException extends Exception{
    public CarException() {}

    public CarException(String message)
    {
        super(message);
    }


}
