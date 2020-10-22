package pl.kskowronski.data.service.egeria.ek;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.ek.AbsencjaRodzaj;

import java.math.BigDecimal;

public interface AbsenceTypeRepo extends JpaRepository<AbsencjaRodzaj, BigDecimal> {
}
