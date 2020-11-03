package pl.kskowronski.data.service.egeria.ek;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.ek.WymiarEtatu;

import java.math.BigDecimal;
import java.util.Optional;

public interface WymiarEtatuRepo extends JpaRepository<WymiarEtatu, BigDecimal>  {

    Optional<WymiarEtatu> findById(int parseInt);

}
