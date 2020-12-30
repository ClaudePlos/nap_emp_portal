package pl.kskowronski.data.service.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.log.LogPit11;

import java.math.BigDecimal;

public class LogPit11Service extends CrudService<LogPit11, BigDecimal>  {

    private LogPit11Repo repo;

    public LogPit11Service(@Autowired LogPit11Repo repo) {
        this.repo = repo;
    }

    @Override
    protected LogPit11Repo getRepository() {
        return repo;
    }

    public void save(LogPit11 logPit11){ repo.save(logPit11);}

}
