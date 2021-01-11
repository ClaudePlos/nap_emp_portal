package pl.kskowronski.data.service.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.log.LogConfirmAcceptation;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class LogConfirmAcceptationService extends CrudService<LogConfirmAcceptation, BigDecimal> {

    private LogConfirmAcceptationRepo repo;

    public LogConfirmAcceptationService(@Autowired LogConfirmAcceptationRepo repo) {
        this.repo = repo;
    }

    @Override
    protected LogConfirmAcceptationRepo getRepository() {
        return repo;
    }

    public Optional<LogConfirmAcceptation> findByPrcId(BigDecimal prcId){return repo.findByPrcId(prcId);}

    public void save(LogConfirmAcceptation logConfirmAcceptation){ repo.save(logConfirmAcceptation);}
}
