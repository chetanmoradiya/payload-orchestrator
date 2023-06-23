package com.cloudtechies.orchestrator.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@Slf4j
@ConditionalOnProperty(value = "simulator.enabled", havingValue="true", matchIfMissing = false)
public class LoadSimulator {

    @Value("${filepoller.ingestionDir}")
    String ingestionDir;

    @Scheduled(fixedRate = 5000)
    void runLoadTest() throws IOException,InterruptedException {

        String rootPath = ingestionDir;
        File rootDir = new File(rootPath);
        List<String> clients = Arrays.asList(rootDir.list());
        String fileHeader = "Transaction ID, Contract Type,Action type,UTI,Level,Reporting Counterparty Code,Reporting Counterparty Financial Status,Reporting Counterparty Sector,Non-Reporting Counterparty Code, Non-Reporting Counterparty Financial Status, Non-Reporting Counterparty Sector, Counterparty Side, Event date, Trading venue,Master agreement type, Value date, General collateral Indicator, Type of asset, Security identifier, Classification of a security, Loan Base product, Loan Sub product, Loan Further sub product,Loan LEI of the issuer, Loan Maturity of the security, Loan Jurisdiction of the issuer";
        List<String> rowsOption  = Arrays.asList(
                "@@_rn_@@,SLEB,NEWT,UTI456@@uti@@,TCTN,ABNJKHJNMJHBNVB@@rccd@@,F,ETFT,ABNJKHJNMJHBNVBNHJ13,F,,TAKE,2015-10-23,M001,OTHR,2015-10-23,SPEC,SECU,ABCDEFGHIJ12,TRNDXX,LBPS,LSPS,LFSP,ABNJKHJNMJHBNVBNHJ12,2015-10-23,US",
                "@@_rn_@@,SLEB,NEWT,UTI457@@uti@@,TCTN,ABNJKHJNMJHBNVB@@rccd@@,F,ETFT,ABNJKHJNMJHBNVBNHJ12,F,,GIVE,2015-10-23,M001,OTHR,2015-10-23,SPEC,SECU,ABCDEFGHIJ12,TRNDXX,LBPS,LSPS,LFSP,ABNJKHJNMJHBNVBNHJ12,2015-10-23,US",
                "@@_rn_@@,SLEB,NEWT,UTI459@@uti@@,TCTN,ABNJKHJNMJHBNVB@@rccd@@,F,ETFT,ABNJKHJNMJHBNVBNHJ15,F,,GIVE,2015-10-23,M001,OTHR,2015-10-23,SPEC,SECU,ABCDEFGHIJ12,,,LSPS,LFSP,,,",
                "@@_rn_@@,SLEB,NEWT,UTI458@@uti@@,TCTN,ABNJKHJNMJHBNVB@@rccd@@,F,ETFT,ABNJKHJNMJHBNVBNHJ15,F,,GIVE,2015-10-23,M001,OTHR,2015-10-23,SPEC,SECU,ABCDEFGHIJ12,,,,,,,"
        );

        String fileNamePrefix = UUID.randomUUID().toString();
        String tmp = ".tmp";
        List<Long> noOfRowsPerFileOptions= Arrays.asList(1L,2L,3L,4L,5L) ;

            String fileToWrite = fileNamePrefix;
            String client = getRandomFromList(clients);
            String filePath = rootPath+ File.separator+client+File.separator+fileToWrite;
            File dir = new File(rootPath+ File.separator+client);
            if(!dir.exists()){
                dir.mkdirs();
            }
            FileWriter fw = new FileWriter(filePath+tmp,true);
            fw.write(fileHeader);
            fw.write("\n");

            Long noOfRowsToWrite = getRandomLongFromList(noOfRowsPerFileOptions);
            log.info("Creating file with {} rows",noOfRowsToWrite);

            for(int j=1;j<=noOfRowsToWrite;j++){
                String row = getRandomFromList(rowsOption);
                row = row.replace("@@_rn_@@",String.valueOf(j));
                row = row.replace("@@uti@@",String.valueOf(j));
                row = row.replace("@@rccd@@",String.valueOf(j));
                fw.write(row+"\n");
                if(j%10==0){
                    fw.flush();
                }
            }
            fw.close();
            File tmpFile = new File(filePath+tmp);
            File finalFile = new File(filePath+".csv");
            tmpFile.renameTo(finalFile);
    }

    private String getRandomFromList(List<String> list){
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    private Long getRandomLongFromList(List<Long> list){
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}
