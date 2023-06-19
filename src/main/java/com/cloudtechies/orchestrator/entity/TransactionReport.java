package com.cloudtechies.orchestrator.entity;

import com.cloudtechies.orchestrator.enums.TransactionStatus;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="transaction_report")
@IdClass(TransactionReportPK.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReport {

    @Id
    @Column(name="txn_report_id",columnDefinition = "UUID")
    private UUID transactionReportId;

    @Column(name="payload_id",columnDefinition = "UUID")
    private UUID payloadId;

    @Column(name="create_ts")
    @Id
    private Instant createTs;

    @Column(name="update_ts")
    private Instant updateTs;

    @Enumerated(EnumType.STRING)
    @Column(name="txn_status")
    private TransactionStatus txnStatus;

    @Column(name="rjct_reason")
    private String rjctReason;

    @Column(name="trn_id")
    private String trnId;

    @Column(name="cntrct_type")
    private String contractType;

    @Column(name="action_type")
    private String actionType;

    @Column(name="uti")
    private String uti;

    @Column(name="level")
    private String level;

    @Column(name="rep_ctrpty_cd")
    private String repCtrPtyCd;

    @Column(name="rep_ctrpty_fin_sts")
    private String repCtrPtyFinSts;

    @Column(name="rep_ctrpty_sec")
    private String repCtrPtySec;

    @Column(name="non_rep_ctrpty_cd")
    private String nonRepCtrPtyCd;

    @Column(name="non_rep_ctrpty_fin_sts")
    private String nonRepCtrPtyFinSts;

    @Column(name="non_rep_ctrpty_sec")
    private String nonRepCtrPtySec;

    @Column(name="ctrpty_side")
    private String ctrPtySide;

    @Column(name="event_date")
    private LocalDate eventDate;

    @Column(name="trading_venue")
    private String tradingVenue;

    @Column(name="mstr_agreement_typ")
    private String mstrAgreementType;

    @Column(name="value_dt")
    private LocalDate valueDt;

    @Column(name="gen_coll_ind")
    private String genCollInd;

    @Column(name="typ_of_asset")
    private String typeOfAsset;

    @Column(name="sec_identifier")
    private String secIdentifier;

    @Column(name="class_of_a_sec")
    private String clsOfASec;

    @Column(name="loan_base_prod")
    private String loanBaseProduct;

    @Column(name="loan_sub_prod")
    private String loanSubProduct;

    @Column(name="loan_fur_sub_prod")
    private String loanFurthrSubProd;

    @Column(name="loan_lei_of_issuer")
    private String loanLeiOfIssuer;

    @Column(name="loan_maturity_of_secu")
    private LocalDate loanMaturityOfSecurity;

    @Column(name="loan_juris_of_issuer")
    private String loanJurisOfIssuer;

}
