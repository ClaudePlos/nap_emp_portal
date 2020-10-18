package pl.kskowronski.data.service.egeria.eDek;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracje;
import pl.kskowronski.data.entity.egeria.ek.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EdktDeklaracjeRepo extends JpaRepository<EdktDeklaracje, BigDecimal> {

    Optional<EdktDeklaracje> findByDklId(BigDecimal dklId);

    Optional<List<EdktDeklaracje>> findAllByDklPrcId(BigDecimal prcId);

}
