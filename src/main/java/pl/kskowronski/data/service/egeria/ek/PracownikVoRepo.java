package pl.kskowronski.data.service.egeria.ek;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.ek.PracownikVo;

import java.math.BigDecimal;
import java.util.Optional;

public interface PracownikVoRepo extends JpaRepository<PracownikVo, BigDecimal> {

    Optional<PracownikVo> findById(BigDecimal prcId);

}
