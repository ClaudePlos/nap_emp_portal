package pl.kskowronski.data.service.egeria.ek;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.ek.EkGroupCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EkGroupCodeRepo extends JpaRepository<EkGroupCode, BigDecimal> {

    Optional<List<EkGroupCode>> findAllByGkDgKodOrderByGkNumer(String dgKod);

}
