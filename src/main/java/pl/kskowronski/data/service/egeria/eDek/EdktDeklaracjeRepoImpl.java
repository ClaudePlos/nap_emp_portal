package pl.kskowronski.data.service.egeria.eDek;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EdktDeklaracjeRepoImpl implements EdktDeklaracjeRepoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void setConsolidate() {
        this.em.createNativeQuery("BEGIN eap_globals.USTAW_konsolidacje('T'); END;")
                //.setParameter("inParam1", inParam1)
                .executeUpdate();
    }


}
