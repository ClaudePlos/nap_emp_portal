package pl.kskowronski.data.entity.egeria.ek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

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
}
