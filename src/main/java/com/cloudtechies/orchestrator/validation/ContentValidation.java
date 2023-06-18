package com.cloudtechies.orchestrator.validation;

import com.cloudtechies.orchestrator.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class ContentValidation implements FileValidation {

    private ValidationStep next;

    @Override
    public void validate(File file) throws ValidationException{
        log.info("Running Content Validation");
    }

    @Override
    public ValidationStep setNext(ValidationStep step) {
        this.next = step;
        return step;
    }


}
