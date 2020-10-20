package pl.kskowronski.data.entity.egeria.global;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "EAT_FIRMY")
public class EatFirma {

    @Id
    @Column(name = "FRM_ID")
    private BigDecimal frmId;

    @Column(name = "FRM_NAZWA")
    private String frmNazwa;

    public EatFirma() {
    }

    public BigDecimal getFrmId() {
        return frmId;
    }

    public void setFrmId(BigDecimal frmId) {
        this.frmId = frmId;
    }

    public String getFrmNazwa() {
        return frmNazwa;
    }

    public void setFrmNazwa(String frmNazwa) {
        this.frmNazwa = frmNazwa;
    }
}
