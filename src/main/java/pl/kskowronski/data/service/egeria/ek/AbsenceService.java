package pl.kskowronski.data.service.egeria.ek;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.egeria.ek.AbsenceDTO;
import pl.kskowronski.data.entity.egeria.ek.Absencja;
import pl.kskowronski.data.service.egeria.global.EatFirmaRepo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbsenceService extends CrudService<Absencja, BigDecimal> {

    private AbsenceRepo repo;

    public AbsenceService(@Autowired AbsenceRepo repo) {
        this.repo = repo;
    }

    @Autowired
    private EatFirmaRepo eatFirmaRepo;

    @Autowired
    private ZwolnienieRepo zwolnienieRepo;

    @Autowired
    private AbsenceTypeRepo absenceTypeRepo;

    @Override
    protected AbsenceRepo getRepository() {
        return repo;
    }

    public List<AbsenceDTO> findAllByAbPrcIdAndAbDAndAdDataOd_Year(BigDecimal prcId, String year) throws Exception {
        repo.setConsolidate();
        Optional<List<Absencja>> listAbsence = repo.findAllByAbPrcIdAndAbDAndAdDataOd_Year(prcId, year);
        if (!listAbsence.isPresent()){
            throw new Exception("Brak absencji w danym roku");
        }
        List<AbsenceDTO> listAbsenceDTO = new ArrayList<>();
        listAbsence.get().stream().forEach( item -> listAbsenceDTO.add( mapperAbsence(item)));
        return listAbsenceDTO;
    }


    private AbsenceDTO mapperAbsence( Absencja ab){
        AbsenceDTO absence = new AbsenceDTO();
        absence.setAbId(ab.getAbId());
        absence.setAbZwolId(ab.getAbZwolId());
        absence.setAbRdaId(ab.getAbRdaId());
        absence.setAbPrcId(ab.getAbPrcId());
        absence.setAbDataOd(ab.getAbDataOd());
        absence.setAbDataDo(ab.getAbDataDo());
        absence.setAbKodFunduszu(ab.getAbKodFunduszu());
        absence.setAbDniWykorzystane(ab.getAbDniWykorzystane());
        absence.setAbGodzinyWykorzystane(ab.getAbGodzinyWykorzystane());
        absence.setAbFanulowana(ab.getAbFanulowana());
        absence.setAbFrmName( eatFirmaRepo.findById( zwolnienieRepo.findById(ab.getAbZwolId()).get().getZwolFrmId()).get().getFrmNazwa() );
        absence.setAbTypeOfAbsence( absenceTypeRepo.findById(ab.getAbRdaId()).get().getRdaNazwa() );
        return absence;
    }

}
