package pl.kskowronski.data.entity.egeria.eDek;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

public class EdktDeklaracjeDTO {

    private BigDecimal dklId;
    private BigDecimal dklStatus;
    private String dklTdlKod;
    private BigDecimal dklPrcId;
    private Date dklDataOd;
    private Date dklDataDo;
    private String dklXmlVisual;
    private BigDecimal dklFrmId;
    private String dklFrmNazwa;
    private String dklYear;


    public BigDecimal getDklId() {
        return dklId;
    }

    public void setDklId(BigDecimal dklId) {
        this.dklId = dklId;
    }

    public BigDecimal getDklStatus() {
        return dklStatus;
    }

    public void setDklStatus(BigDecimal dklStatus) {
        this.dklStatus = dklStatus;
    }

    public String getDklTdlKod() {
        return dklTdlKod;
    }

    public void setDklTdlKod(String dklTdlKod) {
        this.dklTdlKod = dklTdlKod;
    }

    public BigDecimal getDklPrcId() {
        return dklPrcId;
    }

    public void setDklPrcId(BigDecimal dklPrcId) {
        this.dklPrcId = dklPrcId;
    }

    public Date getDklDataOd() {
        return dklDataOd;
    }

    public void setDklDataOd(Date dklDataOd) {
        this.dklDataOd = dklDataOd;
    }

    public Date getDklDataDo() {
        return dklDataDo;
    }

    public void setDklDataDo(Date dklDataDo) {
        this.dklDataDo = dklDataDo;
    }

    public String getDklXmlVisual() {
        return dklXmlVisual;
    }

    public void setDklXmlVisual(String dklXmlVisual) {
        this.dklXmlVisual = dklXmlVisual;
    }

    public BigDecimal getDklFrmId() {
        return dklFrmId;
    }

    public void setDklFrmId(BigDecimal dklFrmId) {
        this.dklFrmId = dklFrmId;
    }

    public String getDklFrmNazwa() {
        return dklFrmNazwa;
    }

    public void setDklFrmNazwa(String dklFrmNazwa) {
        this.dklFrmNazwa = dklFrmNazwa;
    }

    public String getDklYear() {
        return dklYear;
    }

    public void setDklYear(String dklYear) {
        this.dklYear = dklYear;
    }
}
