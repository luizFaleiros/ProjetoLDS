package br.com.projeto.LDS.services;

import br.com.projeto.LDS.config.security.UserDetailSecurity;
import br.com.projeto.LDS.domains.DTO.PersonDTO;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.entities.Studant;
import br.com.projeto.LDS.domains.mappers.PersonMapper;
import br.com.projeto.LDS.enums.PerfilEnum;
import br.com.projeto.LDS.enums.PersonTypeEnum;
import br.com.projeto.LDS.exceptions.AuthorizationException;
import br.com.projeto.LDS.exceptions.DuplicateException;
import br.com.projeto.LDS.exceptions.NotFoundException;
import br.com.projeto.LDS.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PersonService implements BaseService<Person, PersonDTO> {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public List<Person> listAll() {
        List<Person> personList = personRepository.findAll();
        return personList ;
    }

    @Override
    public Person getById(Long id) {

        UserDetailSecurity user = UserServices.athenticated();
        if(user == null || !user.hasRole(PerfilEnum.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Acesso negado");
        }
        return personRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
    }

    @Override
    public void saveAll(List<Person> dtos, String token) {

    }


    @Override
    public void save(Person person) {

        UserDetailSecurity user = UserServices.athenticated();
        if(user == null && !user.hasRole(PerfilEnum.ADMIN)){
            throw new AuthorizationException("Acesso negado");
        }
        person.setModifiedBy(user.getUsername());
        person.setCreatedDate(LocalDate.now());
        person.setModifiedDate(LocalDate.now());
        person.setPass(passwordEncoder.encode(person.getPass()));
        try {
            personRepository.save(person);
        }catch (DataIntegrityViolationException e ){
            throw new DuplicateException("Cadastro duplicado");
        }
        person.setPass(passwordEncoder.encode(person.getPass()));
    }


    @Override
    public Person update(Person person, Long id) {
        UserDetailSecurity user = UserServices.athenticated();
        if(user == null && !user.hasRole(PerfilEnum.ADMIN)){
            throw new AuthorizationException("Acesso negado");
        }
        Person p = getById(id);
        p.setModifiedBy(user.getUsername());
        p.setCpf(person.getCpf());
        p.setLastName(person.getLastName());
        p.setName(person.getName());
        p = personMapper.updateEntity(p,person);
        p.setModifiedDate(LocalDate.now());
        return p;
    }

    @Override
    public Person patch(Map<String, Object> patch, Long id) {
        return null;
    }

    public List<Studant> getAllStudantsIn(List<Long> studants) {
        return personRepository.findByIdInAndPersonType(studants, PersonTypeEnum.STUDANT);
    }

    public List<Studant> saveAllStudants(List<Studant> studants) {
        return personRepository.saveAll(studants);
    }

}
