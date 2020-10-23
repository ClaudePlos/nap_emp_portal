package pl.kskowronski.data.service.egeria.ek;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.ek.Zatrudnienie;

import java.math.BigDecimal;

public interface ZatrudnienieRepo extends JpaRepository<Zatrudnienie, BigDecimal> {
}
