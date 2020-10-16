package pl.kskowronski.data.entity.egeria.ek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "EK_PRACOWNICY")
public class PracownikVo {

    @Id
    @Column(name = "PRC_ID")
    private BigDecimal prcId;

    @Column(name = "PRC_NUMER")
    private BigDecimal prcNumer;

    @Column(name = "PRC_PESEL")
    private String prcPesel;

    public PracownikVo() {
    }

    public BigDecimal getPrcId() {
        return prcId;
    }

    public void setPrcId(BigDecimal prcId) {
        this.prcId = prcId;
    }

    public BigDecimal getPrcNumer() {
        return prcNumer;
    }

    public void setPrcNumer(BigDecimal prcNumer) {
        this.prcNumer = prcNumer;
    }

    public String getPrcPesel() {
        return prcPesel;
    }

    public void setPrcPesel(String prcPesel) {
        this.prcPesel = prcPesel;
    }
}
