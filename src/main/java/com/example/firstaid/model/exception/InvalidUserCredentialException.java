package com.example.firstaid.model.exception;

public class InvalidUserCredentialException extends RuntimeException{
    public InvalidUserCredentialException(){
        super("Invalid user credential exception");//prakjanje na poraka
    }
}
