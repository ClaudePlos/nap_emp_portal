package pl.kskowronski.data.entity.egeria.eDek;

//EDKT_DEKLARACJE

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "EDKT_DEKLARACJE")
public class EdktDeklaracje {

    @Id
    @Column(name = "DKL_ID")
    private BigDecimal dklId;

    @Column(name = "DKL_STATUS")
    private BigDecimal dklStatus;

    @Column(name = "DKL_TDL_KOD") // Pit11
    private BigDecimal dklTdlKod;

    @Column(name = "DKL_PRC_ID")
    private BigDecimal dklPrcId;

    @Column(name = "DKL_DATA_OD")
    private Date dklDataOd;

    @Column(name = "DKL_DATA_DO")
    private Date dklDataDo;

    @Column(name = "DKL_XML_WIZUAL")
    private String dklXmlVisual;

    @Column(name = "DKL_FRM_ID")
    private BigDecimal dklFrmId;

    public EdktDeklaracje() {
    }

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

    public BigDecimal getDklTdlKod() {
        return dklTdlKod;
    }

    public void setDklTdlKod(BigDecimal dklTdlKod) {
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
}
