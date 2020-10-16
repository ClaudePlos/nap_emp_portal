package pl.kskowronski.data.service;

import pl.kskowronski.data.entity.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kskowronski.data.entity.egeria.ek.PracownikVo;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PersonRepository extends JpaRepository<Person, Integer> {


}