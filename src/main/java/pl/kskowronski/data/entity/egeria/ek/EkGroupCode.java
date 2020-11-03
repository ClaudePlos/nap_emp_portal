package pl.kskowronski.data.entity.egeria.ek;

import javax.persistence.*;

@Entity
@Table(name="EK_GRUPY_KODOW")
public class EkGroupCode {

    @Id
    @Column(name = "GK_DG_KOD", nullable = false)
    private String gkDgKod;

    @Column(name = "GK_DSK_ID", nullable = false)
    private Long gkDskId;

    @Column(name = "GK_NUMER", nullable = false)
    private Long gkNumer;

    @Transient
    private Long dskKod;

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

    public Long getGkDskId() {
        return gkDskId;
    }

    public void setGkDskId(Long gkDskId) {
        this.gkDskId = gkDskId;
    }

    public Long getGkNumer() {
        return gkNumer;
    }

    public void setGkNumer(Long gkNumer) {
        this.gkNumer = gkNumer;
    }

    public Long getDskKod() {
        return dskKod;
    }

    public void setDskKod(Long dskKod) {
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
