package pl.kskowronski.data.entity.egeria.ek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ek_zwolnienia")
public class Zwolnienie {

    @Id
    @Column(name = "zwol_id")
    private BigDecimal zwolId;

    @Column(name = "zwol_frm_id")
    private BigDecimal zwolFrmId;

    public Zwolnienie() {
    }

    public BigDecimal getZwolId() {
        return zwolId;
    }

    public void setZwolId(BigDecimal zwolId) {
        this.zwolId = zwolId;
    }

    public BigDecimal getZwolFrmId() {
        return zwolFrmId;
    }

    public void setZwolFrmId(BigDecimal zwolFrmId) {
        this.zwolFrmId = zwolFrmId;
    }
}
