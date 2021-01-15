package br.com.projeto.LDS.services;

import br.com.projeto.LDS.config.security.UserDetailSecurity;
import br.com.projeto.LDS.config.security.jwt.JwtUtil;
import br.com.projeto.LDS.domains.DTO.PersonDTO;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.mappers.PersonMapper;
import br.com.projeto.LDS.enums.PerfilEnum;
import br.com.projeto.LDS.exceptions.AuthorizationException;
import br.com.projeto.LDS.exceptions.DuplicateException;
import br.com.projeto.LDS.exceptions.NotFoundException;
import br.com.projeto.LDS.repositories.PersonRepository;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
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
    private final JwtUtil jwtUtil;


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
    public void saveAll(List<PersonDTO> personDTOS, String token) {

    }


    @Override
    public void save(PersonDTO person) {
        UserDetailSecurity user = UserServices.athenticated();
        if(user == null && !user.hasRole(PerfilEnum.ADMIN)){
            throw new AuthorizationException("Acesso negado");
        }
        Person p;
        p = personMapper.toEntity(person);
        p.setModifiedBy(user.getUsername());
        p.setCreatedDate(LocalDate.now());
        p.setModifiedDate(LocalDate.now());
        p.setPass(passwordEncoder.encode(p.getPass()));
        try {
            personRepository.saveAndFlush(p);
        }catch (DataIntegrityViolationException e ){
            throw new DuplicateException("Cadastro duplicado");
        }
        p.setPass(passwordEncoder.encode(person.getPassword()));
    }

    @Override
    public Person patch(Map<String, Object> patch, Long id) {
        return null;
    }

    @Override
    public Person update(PersonDTO person, Long id) {
        UserDetailSecurity user = UserServices.athenticated();
        if(user == null && !user.hasRole(PerfilEnum.ADMIN)){
            throw new AuthorizationException("Acesso negado");
        }
        Person p = getById(id);
        p.setModifiedBy(user.getUsername());
        p.setCpf(person.getCpf());
        p.setLastName(person.getLastName());
        p.setName(person.getFirstName());
        p = personMapper.updateEntity(p,person);
        p.setModifiedDate(LocalDate.now());
        return p;
    }

}
