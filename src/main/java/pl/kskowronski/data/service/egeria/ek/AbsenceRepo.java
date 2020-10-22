package pl.kskowronski.data.service.egeria.ek;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.ek.AbsenceDTO;
import pl.kskowronski.data.entity.egeria.ek.Absencja;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.service.egeria.eDek.EdktDeklaracjeRepoCustom;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AbsenceRepo extends JpaRepository<Absencja, BigDecimal>, EdktDeklaracjeRepoCustom {

     Optional<List<Absencja>> findAllByAbPrcIdAndAbDAndAdDataOd_Year(BigDecimal prcId, String year);

}
