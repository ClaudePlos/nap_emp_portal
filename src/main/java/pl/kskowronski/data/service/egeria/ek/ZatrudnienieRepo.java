package pl.kskowronski.data.service.egeria.ek;

import javafx.concurrent.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.ek.Zatrudnienie;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface ZatrudnienieRepo extends JpaRepository<Zatrudnienie, BigDecimal> {




}
