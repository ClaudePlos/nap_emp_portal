package pl.kskowronski.data.service.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.admin.NppSkForSupervisor;

import java.math.BigDecimal;
import java.util.List;


@Service
public class NppSkForSupervisorService extends CrudService<NppSkForSupervisor, BigDecimal>  {

    private NppSkForSupervisorRepo repo;

    public NppSkForSupervisorService(@Autowired NppSkForSupervisorRepo repo) {
        this.repo = repo;
    }

    @Override
    protected NppSkForSupervisorRepo getRepository() {
        return repo;
    }

    public List<NppSkForSupervisor> findAll() {
        List<NppSkForSupervisor> list = repo.findAll();
        return list;
    }

    public void save(NppSkForSupervisor nppSkForSupervisor) {
        repo.save(nppSkForSupervisor);
    }

    public void deleteById(BigDecimal id) {
        repo.deleteById(id);
    }

}
