package pl.kskowronski.data.service.log;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.ek.RodzajWymiaru;
import pl.kskowronski.data.entity.log.LogConfirmAcceptation;

import java.math.BigDecimal;
import java.util.Optional;

public interface LogConfirmAcceptationRepo extends JpaRepository<LogConfirmAcceptation, BigDecimal> {

    Optional<LogConfirmAcceptation> findByPrcId(BigDecimal prcId );
    
}
