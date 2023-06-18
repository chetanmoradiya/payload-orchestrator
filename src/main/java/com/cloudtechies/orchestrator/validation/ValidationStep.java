package com.cloudtechies.orchestrator.validation;

import java.io.File;

public interface ValidationStep {

    void validate(File file) throws Exception;

    ValidationStep setNext(ValidationStep step);
}
