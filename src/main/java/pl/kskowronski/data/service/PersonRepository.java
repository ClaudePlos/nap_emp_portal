package pl.kskowronski.data.service;

import pl.kskowronski.data.entity.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {


}