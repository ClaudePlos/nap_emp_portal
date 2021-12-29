package pl.kskowronski.data.entity.admin;

import pl.kskowronski.data.entity.egeria.css.SK;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "npp_sk_for_supervisor")
public class NppSkForSupervisor {

    @Id
    @Column(name = "ID")
    private BigDecimal id;

    @Column(name = "SK_ID")
    private BigDecimal skId;

    @Column(name = "PRC_ID")
    private BigDecimal prcId;

    @Column(name = "PRC_NAZWISKO_IMIE")
    private String prcNazwiskoImie;

    @Column(name = "SK_KOD")
    private String skKod;

    @Transient
    private SK sk;

    public NppSkForSupervisor() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getSkId() {
        return skId;
    }

    public void setSkId(BigDecimal skId) {
        this.skId = skId;
    }

    public BigDecimal getPrcId() {
        return prcId;
    }

    public void setPrcId(BigDecimal prcId) {
        this.prcId = prcId;
    }

    public String getPrcNazwiskoImie() {
        return prcNazwiskoImie;
    }

    public void setPrcNazwiskoImie(String prcNazwiskoImie) {
        this.prcNazwiskoImie = prcNazwiskoImie;
    }
    public String getSkKod() {
        return skKod;
    }

    public void setSkKod(String skKod) {
        this.skKod = skKod;
    }

    public SK getSk() {
        return this.sk;
    }

    public void setSk(SK sk) {
        this.sk = sk;
        this.skId = sk.getSkId();
        this.skKod = sk.getSkKod();
    }
}