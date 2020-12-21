package br.com.projeto.LDS.services;

import br.com.projeto.LDS.domains.DTO.PersonDTO;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.exceptions.PersonNotFoundException;
import br.com.projeto.LDS.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person getById(Long id){
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public void saveAll(List<PersonDTO> personList) {
        //personRepository.saveAll(personList);
    }

    public void save(PersonDTO person) {
        //personRepository.save(person);
    }

    public List<Person> listAll() {
        return personRepository.findAll();
    }

}
