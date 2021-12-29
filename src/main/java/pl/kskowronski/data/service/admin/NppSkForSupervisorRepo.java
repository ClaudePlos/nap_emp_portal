package pl.kskowronski.data.service.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.admin.NppSkForSupervisor;

import java.math.BigDecimal;
import java.util.List;

public interface NppSkForSupervisorRepo extends JpaRepository<NppSkForSupervisor, BigDecimal> {

    List<NppSkForSupervisor> findAllBySkKod(String skKod);

}
