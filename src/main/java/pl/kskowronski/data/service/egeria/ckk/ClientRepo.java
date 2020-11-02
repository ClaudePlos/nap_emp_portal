package pl.kskowronski.data.service.egeria.ckk;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.ckk.Client;

import java.math.BigDecimal;
import java.util.Optional;

public interface ClientRepo  extends JpaRepository<Client, BigDecimal> {

    Optional<Client> getClientByKlKod(BigDecimal klKod);
}
