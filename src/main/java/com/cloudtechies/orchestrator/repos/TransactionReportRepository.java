package com.cloudtechies.orchestrator.repos;

import com.cloudtechies.orchestrator.entity.TransactionReport;
import com.cloudtechies.orchestrator.entity.TransactionReportPK;
import com.cloudtechies.orchestrator.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionReportRepository extends JpaRepository<TransactionReport, TransactionReportPK> {

    Long countByPayloadIdAndTxnStatusIn(@Param("payloadId") UUID payloadId,
                                        @Param("txnStatus") List<TransactionStatus> txnStatus);


}
