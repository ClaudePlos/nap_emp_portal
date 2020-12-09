package pl.kskowronski.data.entity.egeria.ek;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

public class ListaPlacWartKolumnyDTO {

    @Transient
    List<ListaPlacWartKolumnyDTO> listCollumns;
    private String dskSkrot;
    private BigDecimal skWartosc;
    private String opis;
    private String kolumna;

    public String getDskSkrot() {
        return dskSkrot;
    }

    public void setDskSkrot(String dskSkrot) {
        this.dskSkrot = dskSkrot;
    }

    public BigDecimal getSkWartosc() {
        return skWartosc;
    }

    public void setSkWartosc(BigDecimal skWartosc) {
        this.skWartosc = skWartosc;
    }

    public List<ListaPlacWartKolumnyDTO> getListCollumns() {
        return listCollumns;
    }

    public void setListCollumns(List<ListaPlacWartKolumnyDTO> listCollumns) {
        this.listCollumns = listCollumns;
    }

    public String getKolumna() {
        return kolumna;
    }

    public void setKolumna(String kolumna) {
        this.kolumna = kolumna;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
