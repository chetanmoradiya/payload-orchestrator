package com.cloudtechies.orchestrator.exception;

import lombok.Getter;

public class UnrecoverableException extends RuntimeException{

    @Getter
    private final String errorCode;

    public UnrecoverableException(String code, String desc){
        super(desc);
        this.errorCode = code;
    }
}
