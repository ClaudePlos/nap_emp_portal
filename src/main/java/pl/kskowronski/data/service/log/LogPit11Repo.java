package pl.kskowronski.data.service.log;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.log.LogPit11;

import java.math.BigDecimal;

public interface LogPit11Repo extends JpaRepository<LogPit11, BigDecimal> {
}
