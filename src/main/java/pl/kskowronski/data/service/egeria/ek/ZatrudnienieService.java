package pl.kskowronski.data.service.egeria.ek;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.egeria.ek.Zatrudnienie;

import java.math.BigDecimal;

public class ZatrudnienieService extends CrudService<Zatrudnienie, BigDecimal> {

    private ZatrudnienieRepo repo;

    public ZatrudnienieService(@Autowired ZatrudnienieRepo repo) {
        this.repo = repo;
    }

    @Override
    protected ZatrudnienieRepo getRepository() {
        return repo;
    }

}
