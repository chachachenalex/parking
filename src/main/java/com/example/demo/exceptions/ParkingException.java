package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParkingException extends Exception {
    public ParkingException() {}

    public ParkingException(String message)
    {
        super(message);
    }
}
