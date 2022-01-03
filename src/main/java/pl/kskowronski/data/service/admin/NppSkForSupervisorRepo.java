package pl.kskowronski.data.service.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kskowronski.data.entity.admin.NppSkForSupervisor;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracje;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface NppSkForSupervisorRepo extends JpaRepository<NppSkForSupervisor, BigDecimal> {

    List<NppSkForSupervisor> findAllBySkKod(String skKod);

    @Query("select s from NppSkForSupervisor s where s.prcId = :prcId")
    List<NppSkForSupervisor> findSkForSupervisor(@Param("prcId") BigDecimal prcId);


}
