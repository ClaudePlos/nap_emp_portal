package pl.kskowronski.data.service.egeria.ek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kskowronski.data.entity.egeria.ek.EkDefGroup;

import java.util.List;
import java.util.Optional;

public interface EkDefGroupRepo extends JpaRepository<EkDefGroup, String> {

    Optional<List<EkDefGroup>> findAllByDgDkKodOrderByDgNumer(String dgDkKod);

}
