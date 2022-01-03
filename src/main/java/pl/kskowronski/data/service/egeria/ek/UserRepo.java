package pl.kskowronski.data.service.egeria.ek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kskowronski.data.entity.egeria.ek.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, BigDecimal> {

    Optional<User> findById(BigDecimal prcId);

    Optional<User> findByUsername(String username);

    Optional<User> findByPassword(String pesel);

    Optional<List<User>> findByPrcDgKodEkOrderByPrcNazwisko(String dgKod);

}
