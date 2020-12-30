package pl.kskowronski.data.entity.log;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "LOG_PIT11")
public class LogPit11 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private BigDecimal id;

    @Column(name = "PRC_ID")
    private BigDecimal prcId;

    @Column(name = "EVENT")
    private String event;

    @Column(name = "YEAR")
    private BigDecimal year;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AUDIT_DC")
    private Date auditDc;

    public LogPit11() {
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public BigDecimal getYear() {
        return year;
    }

    public void setYear(BigDecimal year) {
        this.year = year;
    }

    public Date getAuditDc() {
        return auditDc;
    }

    public void setAuditDc(Date auditDc) {
        this.auditDc = auditDc;
    }
}
