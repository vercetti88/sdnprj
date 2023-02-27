package com.sdn.prj.repositories;


import com.sdn.prj.entities.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByIin(String iin);
    boolean existsByIin(String iin);
}
