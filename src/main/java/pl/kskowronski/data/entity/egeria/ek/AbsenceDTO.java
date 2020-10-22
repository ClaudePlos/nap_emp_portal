package pl.kskowronski.data.entity.egeria.ek;

import java.math.BigDecimal;
import java.util.Date;

public class AbsenceDTO {

    private BigDecimal abId;
    private BigDecimal abZwolId;
    private BigDecimal abRdaId;
    private BigDecimal abPrcId;
    private Date abDataOd;
    private Date abDataDo;
    private String abKodFunduszu;
    private BigDecimal abDniWykorzystane;
    private BigDecimal abGodzinyWykorzystane;
    private String abFanulowana;
    private String abFrmName;
    private String abTypeOfAbsence;

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

    public String getAbFrmName() {
        return abFrmName;
    }

    public void setAbFrmName(String abFrmName) {
        this.abFrmName = abFrmName;
    }

    public String getAbTypeOfAbsence() {
        return abTypeOfAbsence;
    }

    public void setAbTypeOfAbsence(String abTypeOfAbsence) {
        this.abTypeOfAbsence = abTypeOfAbsence;
    }
}
