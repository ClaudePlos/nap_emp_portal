package pl.kskowronski.data.service.egeria.eDek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracje;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracjeDTO;
import pl.kskowronski.data.service.egeria.global.EatFirmaRepo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EdktDeklaracjeService extends CrudService<EdktDeklaracje, BigDecimal> {

    private EdktDeklaracjeRepo repo;

    @Autowired
    private EatFirmaRepo eatFirmaRepo;

    public EdktDeklaracjeService(@Autowired EdktDeklaracjeRepo repo) {
        this.repo = repo;
    }

    SimpleDateFormat dtYYYY = new SimpleDateFormat("yyyy");

    @Override
    protected EdktDeklaracjeRepo getRepository() {
        return repo;
    }

    public Optional<EdktDeklaracje> findByDklId(BigDecimal dklId){
        repo.setConsolidate();
        return repo.findByDklId(dklId);
    };

    public List<EdktDeklaracjeDTO> findAllByDklPrcId(BigDecimal prcId){
        repo.setConsolidate();
        Optional<List<EdktDeklaracje>> listDek = repo.findAllByDklPrcId(prcId);
        List<EdktDeklaracjeDTO> listDekDTO = new ArrayList<>();
        listDek.get().stream().forEach( item -> listDekDTO.add( mapperEdktDeklaracje(item)));
        return listDekDTO;
    };

    private EdktDeklaracjeDTO mapperEdktDeklaracje( EdktDeklaracje dek){
        EdktDeklaracjeDTO dekDTO = new EdktDeklaracjeDTO();
        dekDTO.setDklId(dek.getDklId());
        dekDTO.setDklFrmId(dek.getDklFrmId());
        dekDTO.setDklPrcId(dek.getDklPrcId());
        dekDTO.setDklDataOd(dek.getDklDataOd());
        dekDTO.setDklDataDo(dek.getDklDataDo());
        dekDTO.setDklStatus(dek.getDklStatus());
        dekDTO.setDklTdlKod(dek.getDklTdlKod());
        dekDTO.setDklXmlVisual(dek.getDklXmlVisual());
        dekDTO.setDklFrmNazwa( eatFirmaRepo.findById(dek.getDklFrmId()).get().getFrmNazwa() );
        dekDTO.setDklYear(dtYYYY.format(dek.getDklDataOd()));
        return dekDTO;
    }



}
