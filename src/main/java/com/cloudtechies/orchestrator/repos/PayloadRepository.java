package com.cloudtechies.orchestrator.repos;

import com.cloudtechies.orchestrator.entity.Payload;
import com.cloudtechies.orchestrator.entity.PayloadPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PayloadRepository extends JpaRepository<Payload, PayloadPK> {

    Optional<Payload> findByFileNameAndLastModifiedTs(@Param("fileName") String fileName, @Param("lastModifiedTs") Instant lastModifiedTs);



}
