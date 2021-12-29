package pl.kskowronski.data.entity.egeria.css;

import com.vaadin.flow.component.HasValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "css_stanowiska_kosztow")
public class SK {

    @Id
    @Column(name = "SK_ID", nullable = false)
    private BigDecimal skId;

    @Column(name = "SK_KOD", nullable = false)
    private String skKod;

    @Column(name = "SK_OPIS", nullable = false)
    private String skOpis;

    public SK() {
    }

    public SK(BigDecimal skId, String skKod, String skOpis) {
        this.skId = skId;
        this.skKod = skKod;
        this.skOpis = skOpis;
    }

    public BigDecimal getSkId() {
        return skId;
    }

    public void setSkId(BigDecimal skId) {
        this.skId = skId;
    }

    public String getSkKod() {
        return skKod;
    }

    public void setSkKod(String skKod) {
        this.skKod = skKod;
    }

    public String getSkOpis() {
        return skOpis;
    }

    public void setSkOpis(String skOpis) {
        this.skOpis = skOpis;
    }
}
