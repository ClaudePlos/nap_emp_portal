package pl.kskowronski.data.entity.egeria.ek;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="EK_GRUPY_KODOW")
public class EkGroupCode {

    @Column(name = "GK_DG_KOD")
    private String gkDgKod;

    @Id
    @Column(name = "GK_DSK_ID")
    private BigDecimal gkDskId;

    @Column(name = "GK_NUMER")
    private BigDecimal gkNumer;

    @Transient
    private BigDecimal dskKod;

    @Transient
    private String dskNazwa;

    @Transient
    private Double wartosc;

    public EkGroupCode() {
    }

    public String getGkDgKod() {
        return gkDgKod;
    }

    public void setGkDgKod(String gkDgKod) {
        this.gkDgKod = gkDgKod;
    }

    public BigDecimal getGkDskId() {
        return gkDskId;
    }

    public void setGkDskId(BigDecimal gkDskId) {
        this.gkDskId = gkDskId;
    }

    public BigDecimal getGkNumer() {
        return gkNumer;
    }

    public void setGkNumer(BigDecimal gkNumer) {
        this.gkNumer = gkNumer;
    }

    public BigDecimal getDskKod() {
        return dskKod;
    }

    public void setDskKod(BigDecimal dskKod) {
        this.dskKod = dskKod;
    }

    public String getDskNazwa() {
        return dskNazwa;
    }

    public void setDskNazwa(String dskNazwa) {
        this.dskNazwa = dskNazwa;
    }

    public Double getWartosc() {
        return wartosc;
    }

    public void setWartosc(Double wartosc) {
        this.wartosc = wartosc;
    }
}
