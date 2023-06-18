package com.cloudtechies.orchestrator.validation;

import com.cloudtechies.orchestrator.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class HeaderValidation implements FileValidation {

    private ValidationStep next;

    private List<String> headers = Arrays.asList(
            "TRANSACTION ID",
            "CONTRACT TYPE",
            "ACTION TYPE",
            "UTI",
            "LEVEL",
            "REPORTING COUNTERPARTY CODE",
            "REPORTING COUNTERPARTY FINANCIAL STATUS",
            "REPORTING COUNTERPARTY SECTOR",
            "NON-REPORTING COUNTERPARTY CODE",
            "NON-REPORTING COUNTERPARTY FINANCIAL STATUS",
            "NON-REPORTING COUNTERPARTY SECTOR",
            "COUNTERPARTY SIDE",
            "EVENT DATE",
            "TRADING VENUE",
            "MASTER AGREEMENT TYPE",
            "VALUE DATE",
            "GENERAL COLLATERAL INDICATOR",
            "TYPE OF ASSET",
            "SECURITY IDENTIFIER",
            "CLASSIFICATION OF A SECURITY",
            "LOAN BASE PRODUCT",
            "LOAN SUB PRODUCT",
            "LOAN FURTHER SUB PRODUCT",
            "LOAN LEI OF THE ISSUER",
            "LOAN MATURITY OF THE SECURITY",
            "LOAN JURISDICTION OF THE ISSUER"
    );

    @Override
    public void validate(File file) throws ValidationException{
        log.info("Running Header Validation");
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String headerLine = br.readLine();
            List<String> headersFromFile = Arrays.asList(headerLine.split(","));
            headersFromFile = headersFromFile.stream().map(i -> i.trim()).map(i-> i.toUpperCase()).collect(Collectors.toList());
            if(!headers.containsAll(headersFromFile)) {
                throw new ValidationException("Header Validation Failed - some headers in file are not expected or missing", "INVALID HEADERS");
            }
        }catch (FileNotFoundException e){
            throw new ValidationException("Exception occured while validating headers - "+e.getMessage(),"FileNotFoundException");
        }catch(IOException e){
            throw new ValidationException("Exception occured while validating headers - "+e.getMessage(),"IOException");
        }
    }

    @Override
    public ValidationStep setNext(ValidationStep step) {
        this.next = step;
        return step;
    }


}
