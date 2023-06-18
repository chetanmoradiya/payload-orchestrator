package com.cloudtechies.orchestrator.exception;

import lombok.Getter;

public class ValidationException extends RuntimeException{

    @Getter
    private final String errorDesc;

    public ValidationException(String message, String errorDesc){
        super(message);
        this.errorDesc = errorDesc;
    }
}
