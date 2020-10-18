package pl.kskowronski.data.service.egeria.eDek;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracje;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EdktDeklaracjeService extends CrudService<EdktDeklaracje, BigDecimal> {

    private EdktDeklaracjeRepo repo;

    public EdktDeklaracjeService(@Autowired EdktDeklaracjeRepo repo) {
        this.repo = repo;
    }

    @Override
    protected EdktDeklaracjeRepo getRepository() {
        return repo;
    }

    public Optional<EdktDeklaracje> findByDklId(BigDecimal dklId){
        repo.setConsolidate();
        return repo.findByDklId(dklId);
    };

    public Optional<List<EdktDeklaracje>> findAllByDklPrcId(BigDecimal prcId){
        repo.setConsolidate();
        return repo.findAllByDklPrcId(prcId);
    };

}
