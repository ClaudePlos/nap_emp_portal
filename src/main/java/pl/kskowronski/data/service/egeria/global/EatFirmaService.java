package pl.kskowronski.data.service.egeria.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.entity.egeria.global.EatFirma;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class EatFirmaService  extends CrudService<EatFirma, BigDecimal> {

    private EatFirmaRepo repo;

    public EatFirmaService(@Autowired EatFirmaRepo repo) {
        this.repo = repo;
    }

    @Override
    protected EatFirmaRepo getRepository() {
        return repo;
    }

    public Optional<EatFirma> findById(BigDecimal frmId){ return repo.findById(frmId);}

}
