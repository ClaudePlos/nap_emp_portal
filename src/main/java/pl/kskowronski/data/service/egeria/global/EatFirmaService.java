package pl.kskowronski.data.service.egeria.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.entity.egeria.global.EatFirma;

import java.math.BigDecimal;

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
}
