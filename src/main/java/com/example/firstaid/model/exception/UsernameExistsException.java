package com.example.firstaid.model.exception;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(String username){
        super(String.format("User with username:%s already exists",username));
    }
}