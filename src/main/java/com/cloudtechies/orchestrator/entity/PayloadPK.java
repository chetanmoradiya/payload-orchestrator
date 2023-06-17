package com.cloudtechies.orchestrator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PayloadPK implements Serializable {

    private UUID payloadId;

    private LocalDateTime createTs;

}