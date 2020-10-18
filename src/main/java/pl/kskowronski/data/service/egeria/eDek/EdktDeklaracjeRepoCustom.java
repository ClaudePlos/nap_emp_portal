package pl.kskowronski.data.service.egeria.eDek;


import org.springframework.transaction.annotation.Transactional;

public interface EdktDeklaracjeRepoCustom {

    @Transactional
    void setConsolidate();
}
