package pl.kskowronski.data.service.egeria.ek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.egeria.ek.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends CrudService<User, BigDecimal> {

    private UserRepo repo;

    public UserService(@Autowired UserRepo repo) {
        this.repo = repo;
    }

    @Override
    protected UserRepo getRepository() {
        return repo;
    }

    public Optional<User> findById(BigDecimal prcId){ return repo.findById(prcId); }

    public Optional<User> findByUsername(String username){ return repo.findByUsername(username);}

    public Optional<User> findByPassword(String pesel){ return repo.findByPassword(pesel);}

    public List<User> findAll(){ return repo.findAll(); }

    public List<User> findByPrcDgKodEk(String dgKod) {
        return repo.findByPrcDgKodEkOrderByPrcNazwisko(dgKod).get();
    }

}
