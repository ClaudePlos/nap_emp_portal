package pl.kskowronski.data.entity.egeria.ek;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ek_absencje")
public class Absencja {

    @Id
    @Column(name = "ab_id")
    private BigDecimal abId;

    @Column(name = "ab_zwol_id", nullable = false)
    private BigDecimal abZwolId;

    @Column(name = "ab_rda_id", nullable = false)
    private BigDecimal abRdaId;

    @Column(name = "ab_prc_id", nullable = false)
    private BigDecimal abPrcId;

    @Column(name = "ab_data_od", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date abDataOd;

    @Column(name = "ab_data_do", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date abDataDo;

    @Column(name = "ab_kod_funduszu")
    private String abKodFunduszu;

    @Column(name = "ab_dni_wykorzystane")
    private BigDecimal abDniWykorzystane;

    @Column(name = "ab_godziny_wykorzystane")
    private BigDecimal abGodzinyWykorzystane;

    @Column(name = "ab_f_anulowana")
    private String abFanulowana;

    public Absencja() {
    }

    public BigDecimal getAbId() {
        return abId;
    }

    public void setAbId(BigDecimal abId) {
        this.abId = abId;
    }

    public BigDecimal getAbZwolId() {
        return abZwolId;
    }

    public void setAbZwolId(BigDecimal abZwolId) {
        this.abZwolId = abZwolId;
    }

    public BigDecimal getAbRdaId() {
        return abRdaId;
    }

    public void setAbRdaId(BigDecimal abRdaId) {
        this.abRdaId = abRdaId;
    }

    public BigDecimal getAbPrcId() {
        return abPrcId;
    }

    public void setAbPrcId(BigDecimal abPrcId) {
        this.abPrcId = abPrcId;
    }

    public Date getAbDataOd() {
        return abDataOd;
    }

    public void setAbDataOd(Date abDataOd) {
        this.abDataOd = abDataOd;
    }

    public Date getAbDataDo() {
        return abDataDo;
    }

    public void setAbDataDo(Date abDataDo) {
        this.abDataDo = abDataDo;
    }

    public String getAbKodFunduszu() {
        return abKodFunduszu;
    }

    public void setAbKodFunduszu(String abKodFunduszu) {
        this.abKodFunduszu = abKodFunduszu;
    }

    public BigDecimal getAbDniWykorzystane() {
        return abDniWykorzystane;
    }

    public void setAbDniWykorzystane(BigDecimal abDniWykorzystane) {
        this.abDniWykorzystane = abDniWykorzystane;
    }

    public BigDecimal getAbGodzinyWykorzystane() {
        return abGodzinyWykorzystane;
    }

    public void setAbGodzinyWykorzystane(BigDecimal abGodzinyWykorzystane) {
        this.abGodzinyWykorzystane = abGodzinyWykorzystane;
    }

    public String getAbFanulowana() {
        return abFanulowana;
    }

    public void setAbFanulowana(String abFanulowana) {
        this.abFanulowana = abFanulowana;
    }
}
