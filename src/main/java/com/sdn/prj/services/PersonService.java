package com.sdn.prj.services;


import com.sdn.prj.entities.Person;
import com.sdn.prj.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private  final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Transactional
    public boolean save(String iin, String name, String number){
        Person person = new Person(iin,name,number);
        if(personRepository.findByIin(iin).isEmpty()) {
            personRepository.save(person);
            return false;
        }else{
            return true;
        }
    }
    public boolean existsByIin(String iin){
        return personRepository.existsByIin(iin);
    }

    @Transactional
    public void setPromocode(String iin, String code){
        Optional<Person> person = personRepository.findByIin(iin);
        person.ifPresent(value -> value.setPromocode(code));
    }


    public Person findByIin(String iin){
        return personRepository.findByIin(iin).orElse(null);
    }
}
