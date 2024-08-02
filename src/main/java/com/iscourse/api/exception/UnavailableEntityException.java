package com.iscourse.api.exception;

public class UnavailableEntityException extends RuntimeException{
    public UnavailableEntityException(String message) {
        super(message);
    }
}
