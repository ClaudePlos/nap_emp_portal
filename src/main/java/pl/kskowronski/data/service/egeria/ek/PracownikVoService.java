package pl.kskowronski.data.service.egeria.ek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.egeria.ek.PracownikVo;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PracownikVoService extends CrudService<PracownikVo, BigDecimal> {

    private PracownikVoRepo repo;

    public PracownikVoService(@Autowired PracownikVoRepo repo) {
        this.repo = repo;
    }

    @Override
    protected PracownikVoRepo getRepository() {
        return repo;
    }

    public Optional<PracownikVo> findById(BigDecimal prcId){
        return repo.findById(prcId);
    }

}
