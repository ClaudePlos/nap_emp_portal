package pl.kskowronski.data.service.egeria.ek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import pl.kskowronski.data.MapperDate;
import pl.kskowronski.data.entity.egeria.ek.AbsenceDTO;
import pl.kskowronski.data.entity.egeria.ek.AbsenceLimitDTO;
import pl.kskowronski.data.entity.egeria.ek.Absencja;
import pl.kskowronski.data.service.egeria.global.ConsolidationService;
import pl.kskowronski.data.service.egeria.global.EatFirmaRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.*;

@Service
public class AbsenceLimitService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ConsolidationService consolidationService;

    @Autowired
    private EatFirmaRepo eatFirmaRepo;

    @Autowired
    private RodzajWymiaruRepo rodzajWymiaruRepo;

    MapperDate mapperDate = new MapperDate();

    public Optional<List<AbsenceLimitDTO>> findAllAbsenceLimitForPrcIdAndYear(BigDecimal prcId, String year, String codeHoliday) throws Exception {
        Optional<List<AbsenceLimitDTO>> listAbLimit = Optional.of(new ArrayList<>());

        //consolidationService.setConsolidateCompany();

        String sql = "select la_prc_id, la_rok, ld_od, ld_do, ld_pozostalo, la_dg_kod, la_frm_id" +
                " from ek_limity_absencji, ek_limity_dane" +
                " where ld_la_id=la_id" +
                " and la_prc_id = " + prcId +
                " and ld_pozostalo != 0" +
                " and la_dg_kod in (" + codeHoliday + ")" +
                " and la_rok = " +  year +
                " and ld_id = (select max(ld_id) from ek_limity_dane where la_dg_kod = la_dg_kod and ld_la_id=la_id)" +
                " and la_frm_id not in (300318, 300311, 300200)"; //marge companies
        Optional<List<Object[]>> results = Optional.ofNullable(em.createNativeQuery(sql).getResultList());
        if (results.isPresent())
        results.get().forEach( item -> listAbLimit.get().add(mapperAbsenceLimit((Object[]) item)));
        return listAbLimit;
    }


    private AbsenceLimitDTO mapperAbsenceLimit( Object[] ob){
        AbsenceLimitDTO absenceLimit = new AbsenceLimitDTO();
        absenceLimit.setPrcId( (BigDecimal) ob[0]);
        absenceLimit.setRok((BigDecimal) ob[1]);
        absenceLimit.setLdOd(mapperDate.dtYYYYMMDD.format((Date) ob[2]));
        absenceLimit.setLdDo(mapperDate.dtYYYYMMDD.format((Date) ob[3]));
        absenceLimit.setPozostaloUrlopu((BigDecimal) ob[4]);
        absenceLimit.setKodUrlopu((String) ob[5]);
        absenceLimit.setFrmNazwa(eatFirmaRepo.findById((BigDecimal) ob[6]).get().getFrmNazwa());
        absenceLimit.setNazwaWymiaru(rodzajWymiaruRepo.findByRwyDgKod((String) ob[5]).get().getRwyNazwa());
        return absenceLimit;
    }



}
