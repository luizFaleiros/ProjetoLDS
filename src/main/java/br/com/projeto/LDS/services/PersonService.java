package br.com.projeto.LDS.services;

import br.com.projeto.LDS.domains.DTO.PersonDTO;
import br.com.projeto.LDS.domains.DTO.ProfessorDTO;
import br.com.projeto.LDS.domains.DTO.StudantDTO;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.mappers.ProfessorMapper;
import br.com.projeto.LDS.domains.mappers.StudantMapper;
import br.com.projeto.LDS.exceptions.PersonNotFoundException;
import br.com.projeto.LDS.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService implements BaseService<Person, PersonDTO> {

    private final PersonRepository personRepository;
    private final StudantMapper studantMapper;
    private final ProfessorMapper professorMapper;

    @Override
    public List<Person> listAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(Long id) {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public void saveAll(List<PersonDTO> personList) {

    }

    @Override
    public void save(PersonDTO person) {
        Person p;
        if (person instanceof ProfessorDTO) {
            p = professorMapper.toEntity((ProfessorDTO) person);
        } else if(person instanceof StudantDTO){
            p = studantMapper.toEntity((StudantDTO) person);
        }else {
            throw new IllegalArgumentException();
        }
        personRepository.save(p);
    }
}
