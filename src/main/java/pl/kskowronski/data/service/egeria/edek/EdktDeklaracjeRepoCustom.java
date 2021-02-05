package pl.kskowronski.data.service.egeria.edek;


import org.springframework.transaction.annotation.Transactional;

public interface EdktDeklaracjeRepoCustom {

    @Transactional
    void setConsolidate();
}
