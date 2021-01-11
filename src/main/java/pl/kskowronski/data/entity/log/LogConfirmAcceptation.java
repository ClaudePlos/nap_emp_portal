package pl.kskowronski.data.entity.log;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "NPP_LOG_CONFIRM_ACCEPTATION")
public class LogConfirmAcceptation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private BigDecimal id;

    @Column(name = "PRC_ID")
    private BigDecimal prcId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AUDIT_DC")
    private Date auditDc;

    @Column(name = "DESCRIPTION")
    private String description;

    public LogConfirmAcceptation() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getPrcId() {
        return prcId;
    }

    public void setPrcId(BigDecimal prcId) {
        this.prcId = prcId;
    }

    public Date getAuditDc() {
        return auditDc;
    }

    public void setAuditDc(Date auditDc) {
        this.auditDc = auditDc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
