package pl.kskowronski.data.entity.egeria.ek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ek_rodzaje_absencji")
public class AbsencjaRodzaj {

    @Id
    @Column(name = "rda_id")
    private BigDecimal rdaId;

    @Column(name = "rda_kod")
    private String rdaKod;

    @Column(name = "rda_nazwa")
    private String rdaNazwa;

    public AbsencjaRodzaj() {
    }

    public BigDecimal getRdaId() {
        return rdaId;
    }

    public void setRdaId(BigDecimal rdaId) {
        this.rdaId = rdaId;
    }

    public String getRdaKod() {
        return rdaKod;
    }

    public void setRdaKod(String rdaKod) {
        this.rdaKod = rdaKod;
    }

    public String getRdaNazwa() {
        return rdaNazwa;
    }

    public void setRdaNazwa(String rdaNazwa) {
        this.rdaNazwa = rdaNazwa;
    }
}
