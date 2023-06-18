package com.cloudtechies.orchestrator.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Jsonifier {

    private Jsonifier(){}

    public static String jsonify(String csv, String headers, String delimiter) throws IOException {
        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).setColumnSeparator(delimiter.charAt(0)).build();
        CsvMapper csvMapper = CsvMapper.builder().enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE).build();
        String jsonString = "";
        try{
            MappingIterator<Object> iterator = csvMapper.readerFor(MyHashMap.class)
                    .with(csvSchema)
                    .readValues(headers+"\n"+csv+"\n"+csv);
            ObjectMapper mapper = new ObjectMapper();
            jsonString = mapper.writeValueAsString(iterator.nextValue());
        }catch (IOException ex){
            log.error("Json Processing Exception - {}",ex.getMessage());
            throw ex;
        }
        return jsonString;
    }
}
