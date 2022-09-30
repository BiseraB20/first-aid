package com.example.firstaid.model.exception;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException() {
        super("Invalid argument exception");
    }
}