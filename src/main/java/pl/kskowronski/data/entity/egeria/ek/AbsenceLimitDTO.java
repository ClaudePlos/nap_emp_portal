package pl.kskowronski.data.entity.egeria.ek;

import java.math.BigDecimal;
import java.util.Date;

public class AbsenceLimitDTO {

    private BigDecimal prcId;
    private BigDecimal rok;
    private String ldOd;
    private String ldDo;
    private BigDecimal pozostaloUrlopu;
    private String kodUrlopu;
    private String nazwaWymiaru;
    private BigDecimal frmId;
    private String frmNazwa;

    public AbsenceLimitDTO() {
    }

    public BigDecimal getPrcId() {
        return prcId;
    }

    public void setPrcId(BigDecimal prcId) {
        this.prcId = prcId;
    }

    public BigDecimal getRok() {
        return rok;
    }

    public void setRok(BigDecimal rok) {
        this.rok = rok;
    }

    public String getLdOd() {
        return ldOd;
    }

    public void setLdOd(String ldOd) {
        this.ldOd = ldOd;
    }

    public String getLdDo() {
        return ldDo;
    }

    public void setLdDo(String ldDo) {
        this.ldDo = ldDo;
    }

    public BigDecimal getPozostaloUrlopu() {
        return pozostaloUrlopu;
    }

    public void setPozostaloUrlopu(BigDecimal pozostaloUrlopu) {
        this.pozostaloUrlopu = pozostaloUrlopu;
    }

    public String getKodUrlopu() {
        return kodUrlopu;
    }

    public void setKodUrlopu(String kodUrlopu) {
        this.kodUrlopu = kodUrlopu;
    }

    public BigDecimal getFrmId() {
        return frmId;
    }

    public void setFrmId(BigDecimal frmId) {
        this.frmId = frmId;
    }

    public String getFrmNazwa() {
        return frmNazwa;
    }

    public void setFrmNazwa(String frmNazwa) {
        this.frmNazwa = frmNazwa;
    }

    public String getNazwaWymiaru() {
        return nazwaWymiaru;
    }

    public void setNazwaWymiaru(String nazwaWymiaru) {
        this.nazwaWymiaru = nazwaWymiaru;
    }
}
