package com.cloudtechies.orchestrator.entity;

import com.cloudtechies.orchestrator.enums.PayloadState;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
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

    @Column(name="absolute_path")
    private String absolutePath;

    @Id
    @Column(name="create_ts")
    private Instant createTs;

    @Column(name="update_ts")
    private Instant updateTs;

    @Column(name="last_modified_ts")
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

}
