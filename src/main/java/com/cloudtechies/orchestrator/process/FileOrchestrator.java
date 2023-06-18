package com.cloudtechies.orchestrator.process;

import com.cloudtechies.orchestrator.entity.Payload;
import com.cloudtechies.orchestrator.enums.PayloadState;
import com.cloudtechies.orchestrator.repos.PayloadRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class FileOrchestrator {

    @Autowired
    PayloadRepository payloadRepository;

    public void createPayload(Exchange exchange){

        String fileName = (String) exchange.getIn().getHeader("CamelFileNameOnly");
        String absolutePath = (String) exchange.getIn().getHeader("CamelFileAbsolutePath");
        Long fileLength = (Long) exchange.getIn().getHeader("CamelFileLength");
        Long lastModified = (Long) exchange.getIn().getHeader("CamelFileLastModified");
        String parentPath = (String) exchange.getIn().getHeader("CamelFileParent");

        Optional<Payload> oPayload = payloadRepository.findByFileNameAndLastModifiedTs(fileName,Instant.ofEpochMilli(lastModified));

        Payload payload;
        if(oPayload.isPresent()){
            log.info("existing payload found by name and last modified timestamp - will update the payload to POLLED");
            payload = oPayload.get();
            payload.setPayloadState(PayloadState.TO_PROCESS);
            payload.setUpdateTs(Instant.now());
            payload.setRespFileName(null);
            payload.setInstructionCount(null);
            payload.setRespFilePath(null);
        }else{
            payload = Payload.builder()
                    .createTs(Instant.now())
                    .payloadId(UUID.randomUUID())
                    .absolutePath(updatePathForStaging(parentPath,fileName))
                    .fileName(fileName)
                    .lastModifiedTs(Instant.ofEpochMilli(lastModified))
                    .payloadState(PayloadState.TO_PROCESS)
                    .build();
        }

        payloadRepository.save(payload);

        log.info("Payload - {} created/updated for file - {} with status POLLED",payload.getPayloadId(),absolutePath);
    }

    private String updatePathForStaging(String parentPath, String fileName){
        return parentPath+ File.separator+".staging"+File.separator+fileName;
    }

}
