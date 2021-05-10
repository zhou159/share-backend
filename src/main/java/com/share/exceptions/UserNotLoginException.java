package com.share.exceptions;

public class UserNotLoginException extends RuntimeException{
    public UserNotLoginException(String message) {
        super(message);
    }
}
