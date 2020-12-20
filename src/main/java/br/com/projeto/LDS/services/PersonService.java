package br.com.projeto.LDS.services;

import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person getById(Long id){
        Person entity = personRepository.findById(id).orElse(null);
        return entity;
    }
}
