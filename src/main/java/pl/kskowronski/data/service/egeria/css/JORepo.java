package pl.kskowronski.data.service.egeria.css;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.css.JO;

import java.math.BigDecimal;

public interface JORepo extends JpaRepository<JO, BigDecimal> {
}
