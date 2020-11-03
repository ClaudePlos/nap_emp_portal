package pl.kskowronski.data.entity.egeria.ek;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="EK_DEF_GRUP")
public class EkDefGroup {

    @Id
    @Column(name = "DG_KOD", nullable = false)
    private String dgKod;

    @Column(name = "DG_NUMER", nullable = false)
    private Long dgNumer;

    @Column(name = "DG_DK_KOD", nullable = false)
    private String dgDkKod;

    @Transient
    private List<EkGroupCode> ekGrupyKodow;

    public EkDefGroup() {
    }

    public String getDgKod() {
        return dgKod;
    }

    public void setDgKod(String dgKod) {
        this.dgKod = dgKod;
    }

    public Long getDgNumer() {
        return dgNumer;
    }

    public void setDgNumer(Long dgNumer) {
        this.dgNumer = dgNumer;
    }

    public String getDgDkKod() {
        return dgDkKod;
    }

    public void setDgDkKod(String dgDkKod) {
        this.dgDkKod = dgDkKod;
    }

    public List<EkGroupCode> getEkGrupyKodow() {
        return ekGrupyKodow;
    }

    public void setEkGrupyKodow(List<EkGroupCode> ekGrupyKodow) {
        this.ekGrupyKodow = ekGrupyKodow;
    }
}
