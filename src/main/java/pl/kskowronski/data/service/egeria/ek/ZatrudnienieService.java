package pl.kskowronski.data.service.egeria.ek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.entity.egeria.ek.WymiarEtatu;
import pl.kskowronski.data.entity.egeria.ek.Zatrudnienie;
import pl.kskowronski.data.service.egeria.global.ConsolidationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ZatrudnienieService extends CrudService<Zatrudnienie, BigDecimal> {

    @PersistenceContext
    private EntityManager em;

    private ZatrudnienieRepo repo;

    public ZatrudnienieService(@Autowired ZatrudnienieRepo repo) {
        this.repo = repo;
    }

    @Override
    protected ZatrudnienieRepo getRepository() {
        return repo;
    }

    @Autowired
    ConsolidationService consolidationService;

    @Autowired
    WymiarEtatuRepo wymiarEtatuRepo;

    private SimpleDateFormat dfYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

    public Optional<List<Zatrudnienie>> getActualContractForWorker(BigDecimal prcId, String period) throws ParseException {
        consolidationService.setConsolidateCompany();
        Date dataOd = dfYYYYMMDD.parse(period + "-01");
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataOd);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); //last day month
        Date dataDo = cal.getTime();

        Optional<List<Zatrudnienie>> contracts = Optional.ofNullable(em.createQuery("select z from Zatrudnienie z where z.zatPrcId = :prcId "
                + "and z.zatDataZmiany <= :dataDo and COALESCE(z.zatDataDo, :dataOd) >= :dataOd "
                + "and z.zatTypUmowy = 0")
                .setParameter("prcId", prcId)
                .setParameter("dataOd", dataOd, TemporalType.DATE)
                .setParameter("dataDo", dataDo, TemporalType.DATE)
                .getResultList());
        return contracts;
    }



    public List<User> getPracownikZatrudNaSkMc(BigDecimal prcIdForm, String okres, BigDecimal frmId, Long typeContract ){
        consolidationService.setConsolidateCompanyOnCompany(frmId);
        List<User> listaAktPracNaSk = new ArrayList<User>();
        // todo KS usunąć stanowiska kosztow administracji
        String sql = "select distinct prc_id, prc_numer, prc_nazwisko, prc_imie, prc_pesel, zat_wymiar, zat_status\n" +
                "from ek_zatrudnienie, ek_pracownicy\n";
        sql += "where (NVL(zat_data_do, to_date('2099', 'YYYY')) >= to_date('" + okres + "', 'YYYY-MM')\n" +
                "and zat_data_zmiany <= last_day(to_date('" + okres + "', 'YYYY-MM')))\n" +
                "and zat_typ_umowy = " + typeContract + "\n" +
                "and zat_prc_id = prc_id\n";
        sql += "and zat_prc_id = " + prcIdForm + " order by prc_nazwisko, prc_imie";

        List result = em.createNativeQuery(sql).getResultList();
        for (Iterator iter = result.iterator(); iter.hasNext();)
        {
            Object[] ob = (Object[]) iter.next();
            User prac = new User();
            BigDecimal prcId = (BigDecimal) ob[0];
            BigDecimal prcNumer = (BigDecimal) ob[1];
            prac.setPrcId(BigDecimal.valueOf(Long.parseLong(prcId.toString())));
            prac.setPrcNumer(BigDecimal.valueOf(Long.parseLong(prcNumer.toString())));
            prac.setPrcNazwisko((String) ob[2]);
            prac.setPrcImie((String) ob[3]);
            prac.setPrcPesel((String) ob[4]);

            if ( typeContract == 0 ){
                Zatrudnienie zat = new Zatrudnienie();
                List<Zatrudnienie> listZat = new ArrayList<>();
                Optional<WymiarEtatu> wymEtatu =  wymiarEtatuRepo.findById(Integer.parseInt(((BigDecimal) ob[5]).toString()));
                zat.setWymiarEtatu(wymEtatu.get());
                zat.setDef0( ((BigDecimal) ob[6]).toString() );
                listZat.add(zat);
                prac.setZatrudnienia(listZat);
            }

            listaAktPracNaSk.add(prac);
        }
        return listaAktPracNaSk;
    }


}
