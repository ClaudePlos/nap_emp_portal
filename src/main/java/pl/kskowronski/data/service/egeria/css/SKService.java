package pl.kskowronski.data.service.egeria.css;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import pl.kskowronski.data.entity.egeria.css.SK;
import pl.kskowronski.data.service.egeria.ckk.ClientRepo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SKService extends CrudService<SK, BigDecimal> {

    private List<SK> list  = new ArrayList<>();

    private SKRepo repo;

    public SKService(@Autowired SKRepo repo) {
        this.repo = repo;

        SK sk = new SK(BigDecimal.valueOf(100725), "AINF","Dział Informatyczny");
        SK sk1 = new SK(BigDecimal.valueOf(108469),"AKCF","Kadry Cała Firma");
        list.add(sk);
        list.add(sk1);
    }

    @Override
    protected SKRepo getRepository() {
        return repo;
    }


    public List<SK> findAll(){
        return list;
        //return repo.findAll();
    }

    public SK findBySkKod( String skKod){
        return list.stream().filter( item -> item.getSkKod().equals(skKod)).collect(Collectors.toList()).get(0);
        //return repo.findAll();
    }

}
