package com.cloudtechies.orchestrator.validation;

import com.cloudtechies.orchestrator.exception.ValidationException;

import java.io.File;

public interface FileValidation extends ValidationStep{

    void validate(File file) throws ValidationException;
}
