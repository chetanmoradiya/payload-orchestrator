package com.cloudtechies.orchestrator.entity;

import com.cloudtechies.orchestrator.enums.PayloadState;
import com.cloudtechies.orchestrator.util.InstantToEpochMilliConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity(name="payload")
@Getter
@Setter
@Table(name="payload")
@IdClass(PayloadPK.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payload {

    @Id
    @Column(name="payload_id",columnDefinition = "UUID")
    private UUID payloadId;

    @Column(name="file_name")
    private String fileName;

    @Column(name="ftp_folder")
    private String ftpFolder;

    @Column(name="absolute_path")
    private String absolutePath;

    @Id
    @Column(name="create_ts")
    @JsonSerialize(converter = InstantToEpochMilliConverter.class)
    private Instant createTs;

    @Column(name="update_ts")
    @JsonSerialize(converter = InstantToEpochMilliConverter.class)
    private Instant updateTs;

    @Column(name="last_modified_ts")
    @JsonSerialize(converter = InstantToEpochMilliConverter.class)
    private Instant lastModifiedTs;

    @Column(name="instruction_count")
    private Long instructionCount;

    @Column(name="resp_file_name")
    private String respFileName;

    @Column(name="resp_file_path")
    private String respFilePath;

    @Enumerated(EnumType.STRING)
    @Column(name="payload_state")
    private PayloadState payloadState;

    @Column(name="rejection_reason")
    private String rejectionReason;

}
