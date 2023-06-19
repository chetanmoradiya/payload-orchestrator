package com.cloudtechies.orchestrator.util;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.Instant;

public class InstantToEpochMilliConverter extends StdConverter<Instant, Long> {

    @Override
    public Long convert(Instant time){
        return time.toEpochMilli();
    }
}
