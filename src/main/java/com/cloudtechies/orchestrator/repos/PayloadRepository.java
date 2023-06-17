package com.cloudtechies.orchestrator.repos;

import com.cloudtechies.orchestrator.entity.Payload;
import com.cloudtechies.orchestrator.entity.PayloadPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayloadRepository extends JpaRepository<Payload, PayloadPK> {


}
