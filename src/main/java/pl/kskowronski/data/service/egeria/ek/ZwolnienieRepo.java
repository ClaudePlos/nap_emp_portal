package pl.kskowronski.data.service.egeria.ek;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.ek.Zwolnienie;
import java.math.BigDecimal;

public interface ZwolnienieRepo extends JpaRepository<Zwolnienie, BigDecimal> {
}
