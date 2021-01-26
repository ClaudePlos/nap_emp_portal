package pl.kskowronski.data.service.egeria.eDek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracje;
import pl.kskowronski.data.entity.egeria.ek.Absencja;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EdktDeklaracjeRepo extends JpaRepository<EdktDeklaracje, BigDecimal>, EdktDeklaracjeRepoCustom {

    Optional<EdktDeklaracje> findByDklId(BigDecimal dklId);

    @Query("select e from EdktDeklaracje e where e.dklPrcId = :prcId and e.dklDataOd>= :dateFrom and e.dklDataDo <= :dateTo " +
            "and e.dklStatus in (50) " +
            "order by e.dklDataOd desc")
    Optional<List<EdktDeklaracje>> findAllByDklPrcIdForYear(@Param("prcId") BigDecimal prcId
            , @Param("dateFrom") Date dateFrom
            , @Param("dateTo") Date dateTo);

}
