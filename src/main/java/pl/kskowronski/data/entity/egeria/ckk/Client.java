package pl.kskowronski.data.entity.egeria.ckk;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "napwfv_kontrahenci")
public class Client {

    @Id
    @Column(name="kl_kod")
    private BigDecimal klKod;

    @Column(name="kld_Id")
    private BigDecimal kldId;

    @Column(name="kl_skrot")
    private String klSkrot;

    @Column(name="kld_typ")
    private String kldTyp;

    @Column(name="kld_nazwa")
    private String kldNazwa;


    @Column(name="kld_nip")
    private String kldNip;

    @Column(name="kld_pesel")
    private String kldPesel;

    @Column(name="kld_regon")
    private String kldRegon;

    @Column(name="kld_forma_wlasnosci")
    private String kldFormaWlasnosci;

    @Column(name="kld_zatwierdzony")
    private String kldZatwierdzony;

    @Column(name="kld_miejsce_urodzenia")
    private String kldMiejsceUrodzenia;

    @Column(name="kld_krs")
    private String kldKrs;

    @Column(name="kld_pkd")
    private String kldPkd;

    // address fields
    @Column(name="adr_woj_id")
    private BigDecimal adrWojId;

    @Column(name="adr_lp")
    private BigDecimal adrLp;

    @Column(name="adr_miejscowosc")
    private String adrMiejscowosc;

    @Column(name="adr_typ_ulicy")
    private String adrTypUlicy;

    @Column(name="adr_zatwierdzony")
    private String adrZatwierdzony;

    @Column(name="adr_f_aktualne")
    private String adrAktualne;

    @Column(name="adr_gmina")
    private String adrGmina;

    @Column(name="adr_kod_pocztowy")
    private String adrKodPocztowy;

    @Column(name="adr_ulica")
    private String adrUlica;

    @Column(name="adr_numer_domu")
    private String adrNumerDomu;

    @Column(name="adr_numer_lokalu")
    private String adrNumerLokalu;

    @Column(name="adr_poczta")
    private String adrPoczta;

    @Column(name="adr_powiat")
    private String adrPowiat;

    public Client() {
    }

    public BigDecimal getKlKod() {
        return klKod;
    }

    public void setKlKod(BigDecimal klKod) {
        this.klKod = klKod;
    }

    public BigDecimal getKldId() {
        return kldId;
    }

    public void setKldId(BigDecimal kldId) {
        this.kldId = kldId;
    }

    public String getKlSkrot() {
        return klSkrot;
    }

    public void setKlSkrot(String klSkrot) {
        this.klSkrot = klSkrot;
    }

    public String getKldTyp() {
        return kldTyp;
    }

    public void setKldTyp(String kldTyp) {
        this.kldTyp = kldTyp;
    }

    public String getKldNazwa() {
        return kldNazwa;
    }

    public void setKldNazwa(String kldNazwa) {
        this.kldNazwa = kldNazwa;
    }

    public String getKldNip() {
        return kldNip;
    }

    public void setKldNip(String kldNip) {
        this.kldNip = kldNip;
    }

    public String getKldPesel() {
        return kldPesel;
    }

    public void setKldPesel(String kldPesel) {
        this.kldPesel = kldPesel;
    }

    public String getKldRegon() {
        return kldRegon;
    }

    public void setKldRegon(String kldRegon) {
        this.kldRegon = kldRegon;
    }

    public String getKldFormaWlasnosci() {
        return kldFormaWlasnosci;
    }

    public void setKldFormaWlasnosci(String kldFormaWlasnosci) {
        this.kldFormaWlasnosci = kldFormaWlasnosci;
    }

    public String getKldZatwierdzony() {
        return kldZatwierdzony;
    }

    public void setKldZatwierdzony(String kldZatwierdzony) {
        this.kldZatwierdzony = kldZatwierdzony;
    }

    public String getKldMiejsceUrodzenia() {
        return kldMiejsceUrodzenia;
    }

    public void setKldMiejsceUrodzenia(String kldMiejsceUrodzenia) {
        this.kldMiejsceUrodzenia = kldMiejsceUrodzenia;
    }

    public String getKldKrs() {
        return kldKrs;
    }

    public void setKldKrs(String kldKrs) {
        this.kldKrs = kldKrs;
    }

    public String getKldPkd() {
        return kldPkd;
    }

    public void setKldPkd(String kldPkd) {
        this.kldPkd = kldPkd;
    }

    public BigDecimal getAdrWojId() {
        return adrWojId;
    }

    public void setAdrWojId(BigDecimal adrWojId) {
        this.adrWojId = adrWojId;
    }

    public BigDecimal getAdrLp() {
        return adrLp;
    }

    public void setAdrLp(BigDecimal adrLp) {
        this.adrLp = adrLp;
    }

    public String getAdrMiejscowosc() {
        return adrMiejscowosc;
    }

    public void setAdrMiejscowosc(String adrMiejscowosc) {
        this.adrMiejscowosc = adrMiejscowosc;
    }

    public String getAdrTypUlicy() {
        return adrTypUlicy;
    }

    public void setAdrTypUlicy(String adrTypUlicy) {
        this.adrTypUlicy = adrTypUlicy;
    }

    public String getAdrZatwierdzony() {
        return adrZatwierdzony;
    }

    public void setAdrZatwierdzony(String adrZatwierdzony) {
        this.adrZatwierdzony = adrZatwierdzony;
    }

    public String getAdrAktualne() {
        return adrAktualne;
    }

    public void setAdrAktualne(String adrAktualne) {
        this.adrAktualne = adrAktualne;
    }

    public String getAdrGmina() {
        return adrGmina;
    }

    public void setAdrGmina(String adrGmina) {
        this.adrGmina = adrGmina;
    }

    public String getAdrKodPocztowy() {
        return adrKodPocztowy;
    }

    public void setAdrKodPocztowy(String adrKodPocztowy) {
        this.adrKodPocztowy = adrKodPocztowy;
    }

    public String getAdrUlica() {
        return adrUlica;
    }

    public void setAdrUlica(String adrUlica) {
        this.adrUlica = adrUlica;
    }

    public String getAdrNumerDomu() {
        return adrNumerDomu;
    }

    public void setAdrNumerDomu(String adrNumerDomu) {
        this.adrNumerDomu = adrNumerDomu;
    }

    public String getAdrNumerLokalu() {
        return adrNumerLokalu;
    }

    public void setAdrNumerLokalu(String adrNumerLokalu) {
        this.adrNumerLokalu = adrNumerLokalu;
    }

    public String getAdrPoczta() {
        return adrPoczta;
    }

    public void setAdrPoczta(String adrPoczta) {
        this.adrPoczta = adrPoczta;
    }

    public String getAdrPowiat() {
        return adrPowiat;
    }

    public void setAdrPowiat(String adrPowiat) {
        this.adrPowiat = adrPowiat;
    }
}
