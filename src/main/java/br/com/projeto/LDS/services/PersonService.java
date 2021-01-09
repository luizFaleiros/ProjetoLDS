package br.com.projeto.LDS.services;

import br.com.projeto.LDS.config.security.UserDetailSecurity;
import br.com.projeto.LDS.config.security.jwt.JwtUtil;
import br.com.projeto.LDS.domains.DTO.PersonDTO;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.mappers.PersonMapper;
import br.com.projeto.LDS.enums.PerfilEnum;
import br.com.projeto.LDS.exceptions.AuthorizationException;
import br.com.projeto.LDS.exceptions.NotFoundException;
import br.com.projeto.LDS.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
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
    public void saveAll(List<PersonDTO> personList,String token) {

    }

    @Override
    public void save(PersonDTO person,String token) {
        Person p;
        p = personMapper.toEntity(person);
        p.setModifiedBy(jwtUtil.getUsername(token));
        p.setCreatedDate(LocalDate.now());
        p.setModifiedDate(LocalDate.now());
        p.setPass(passwordEncoder.encode(p.getPass()));
        personRepository.save(p);
        p.setPass(passwordEncoder.encode(person.getPassword()));
    }

    @Override
    public Person update(PersonDTO person, Long id,String token) {
        Person p = getById(id);
        p.setModifiedBy(jwtUtil.getUsername(token));
        p.setCpf(person.getCpf());
        p.setLastName(person.getLastName());
        p.setName(person.getFirstName());
        p = personMapper.updateEntity(p,person);
        p.setModifiedDate(LocalDate.now());
        return p;
    }

    @Override
    public Person patch(Map<String, Object> patch, Long id,String token) {
        return null;
    }

}
