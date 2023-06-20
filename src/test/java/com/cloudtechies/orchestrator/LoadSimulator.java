package com.cloudtechies.orchestrator;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LoadSimulator {

    @Test
    void runLoadTest() throws IOException,InterruptedException {

        String rootPath = "C:/anand/temp/mnt/ingestion/";
        List<String> clients = Arrays.asList("Client1", "Client2");
        String fileHeader = "Transaction ID, Contract Type,Action type,UTI,Level,Reporting Counterparty Code,Reporting Counterparty Financial Status,Reporting Counterparty Sector,Non-Reporting Counterparty Code, Non-Reporting Counterparty Financial Status, Non-Reporting Counterparty Sector, Counterparty Side, Event date, Trading venue,Master agreement type, Value date, General collateral Indicator, Type of asset, Security identifier, Classification of a security, Loan Base product, Loan Sub product, Loan Further sub product,Loan LEI of the issuer, Loan Maturity of the security, Loan Jurisdiction of the issuer";
        List<String> rowsOption  = Arrays.asList(
                "@@_rn_@@,SLEB,NEWT,UTI456@@uti@@,TCTN,ABNJKHJNMJHBNVB@@rccd@@,F,ETFT,ABNJKHJNMJHBNVBNHJ13,F,,TAKE,12/21/2015,M001,OTHR,9/30/2013,SPEC,SECU,ABCDEFGHIJ12,TRNDXX,LBPS,LSPS,LFSP,ABNJKHJNMJHBNVBNHJ12,12/31/2012,US",
                "@@_rn_@@,SLEB,NEWT,UTI459@@uti@@,TCTN,ABNJKHJNMJHBNVB@@rccd@@,F,ETFT,ABNJKHJNMJHBNVBNHJ15,F,,GIVE,12/21/2015,M001,OTHR,9/30/2013,SPEC,SECU,ABCDEFGHIJ12,,,LSPS,LFSP,,,",
                "@@_rn_@@,SLEB,NEWT,UTI458@@uti@@,TCTN,ABNJKHJNMJHBNVB@@rccd@@,F,ETFT,ABNJKHJNMJHBNVBNHJ15,F,,GIVE,12/21/2015,M001,OTHR,9/30/2013,SPEC,SECU,ABCDEFGHIJ12,,,,,,,"
        );

        String fileNamePrefix = "TradeData_000";
        String fileNameSuffix = ".csv";

        Long noOfFiles = 20L;
        List<Long> noOfRowsPerFileOptions= Arrays.asList(500L,1000L) ;
        int delayBetweenFiles = 10;

        for(int i=1;i<=noOfFiles;i++){
            String fileToWrite = fileNamePrefix+i+fileNameSuffix;
            String client = getRandomFromList(clients);
            String filePath = rootPath+File.separator+client+File.separator+fileToWrite;
            FileWriter fw = new FileWriter(filePath,true);
            fw.write(fileHeader);
            fw.write("\n");
            for(int j=1;j<=getRandomLongFromList(noOfRowsPerFileOptions);j++){
                String row = getRandomFromList(rowsOption);
                row = row.replace("@@_rn_@@",String.valueOf(j));
                row = row.replace("@@uti@@",String.valueOf(j));
                row = row.replace("@@rccd@@",String.valueOf(j));
                fw.write(row+"\n");
                fw.flush();
            }
            fw.close();
            Thread.sleep(1000*delayBetweenFiles);
        }




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
