package com.cloudtechies.orchestrator.repos;

import com.cloudtechies.orchestrator.entity.Payload;
import com.cloudtechies.orchestrator.entity.PayloadPK;
import com.cloudtechies.orchestrator.enums.PayloadState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface PayloadRepository extends JpaRepository<Payload, PayloadPK> {

    Optional<Payload> findByFileNameAndLastModifiedTs(@Param("fileName") String fileName, @Param("lastModifiedTs") Instant lastModifiedTs);

    @Query("select p from payload p where p.payloadState = :payloadState order by p.updateTs DESC, p.createTs DESC")
    List<Payload> findByPayloadStateOrderByUpdateTsDescCreateTsDesc(@Param("payloadState") PayloadState state);



}
