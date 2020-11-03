package pl.kskowronski.data.entity.egeria.ek;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "EK_PRACOWNICY")
public class User {

    @Id
    @Column(name = "PRC_ID")
    private BigDecimal prcId;

    @Column(name = "PRC_NUMER")
    private BigDecimal prcNumer;

    @Column(name = "PRC_DOWOD_OSOB")
    private String username;

    @Column(name = "PRC_PESEL")
    private String password;

    @Column(name = "PRC_PESEL")
    private String prcPesel;

    @Column(name = "PRC_IMIE")
    private String prcImie;

    @Column(name = "PRC_NAZWISKO")
    private String prcNazwisko;

    @Transient
    private List<Zatrudnienie> zatrudnienia;

    public String getNazwImie () {
        return prcNazwisko + " " + prcImie;
    }

    public User() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrcImie() {
        return prcImie;
    }

    public void setPrcImie(String prcImie) {
        this.prcImie = prcImie;
    }

    public String getPrcNazwisko() {
        return prcNazwisko;
    }

    public void setPrcNazwisko(String prcNazwisko) {
        this.prcNazwisko = prcNazwisko;
    }

    public String getPrcPesel() {
        return prcPesel;
    }

    public void setPrcPesel(String prcPesel) {
        this.prcPesel = prcPesel;
    }

    public List<Zatrudnienie> getZatrudnienia() {
        return zatrudnienia;
    }

    public void setZatrudnienia(List<Zatrudnienie> zatrudnienia) {
        this.zatrudnienia = zatrudnienia;
    }


}
