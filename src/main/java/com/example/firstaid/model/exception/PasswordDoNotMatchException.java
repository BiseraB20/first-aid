package com.example.firstaid.model.exception;

public class PasswordDoNotMatchException extends RuntimeException{
    public PasswordDoNotMatchException(){
        super("Password do not match exception");
    }
}