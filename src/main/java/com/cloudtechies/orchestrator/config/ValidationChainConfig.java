package com.cloudtechies.orchestrator.config;

import com.cloudtechies.orchestrator.validation.FileValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@Slf4j
public class ValidationChainConfig {

    @Autowired
    List<FileValidation> fileValidation;

    @PostConstruct
    public void configureValidationChain(){
        log.info("Validation Classes detected - {}",fileValidation.size());

        log.info("Validation Sequence is - ");
        for(int i=0;i<fileValidation.size()-1;i++){
            log.info("Validation {} -> {}",i,fileValidation.get(i).getClass().getCanonicalName());
            fileValidation.get(i).setNext(fileValidation.get(i+1));
        }
        log.info("Validation {} -> {}",fileValidation.size()-1,fileValidation.get(fileValidation.size()-1).getClass().getCanonicalName());
    }

}
