package br.com.projeto.LDS.services;

import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person getById(Long id){
        Person entity = personRepository.findById(id).orElse(null);
        return entity;
    }

    public void saveAll(List<Person> personList) {
        personRepository.saveAll(personList);
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public List<Person> listAll() {
        return personRepository.findAll();
    }
}
