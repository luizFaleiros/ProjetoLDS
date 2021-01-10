package br.com.projeto.LDS.services;

import br.com.projeto.LDS.config.security.UserDetailSecurity;
import br.com.projeto.LDS.domains.DTO.TCCDTO;
import br.com.projeto.LDS.domains.DTO.filter.params.TccFilter;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.entities.Studant;
import br.com.projeto.LDS.domains.entities.TCC;
import br.com.projeto.LDS.domains.mappers.PersonMapper;
import br.com.projeto.LDS.domains.mappers.TccMapper;
import br.com.projeto.LDS.enums.PerfilEnum;
import br.com.projeto.LDS.exceptions.AuthorizationException;
import br.com.projeto.LDS.exceptions.NotContentException;
import br.com.projeto.LDS.repositories.PersonRepository;
import br.com.projeto.LDS.repositories.TccRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TccService {
    private final TccRepository tccRepository;
    private final PersonService personService;
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final TccMapper tccMapper;


    public Page<TCC> getAllByProfessor(TccFilter filter) {
        UserDetailSecurity user = UserServices.athenticated();
        Person person = personService.getById(user.getId());
        if (Objects.isNull(user)) {
            throw new AuthorizationException("Usuario não logado");
        }
        PageRequest page = montTccFilterPageRequest(filter);
        Page<TCC> tccs = tccRepository.findByProfessor((Professor) person, page);
        if (tccs.isEmpty()) {
            throw new NotContentException("Não existe tccs para esse usuario");
        }
        return tccs;
    }

    private PageRequest montTccFilterPageRequest(TccFilter filter) {
        return PageRequest.of(Optional.ofNullable(filter.getPage()).orElse(0),
                Optional.ofNullable(filter.getLinesPerPage()).orElse(10),
                Optional.ofNullable(filter.getDirection()).orElse(Sort.Direction.ASC),
                Optional.ofNullable(filter.getOrderBy()).orElse("id"));
    }

    public TCC getByStudant() {
        UserDetailSecurity user = UserServices.athenticated();
        Person person = personService.getById(user.getId());
        return tccRepository.findByStudantsIn(Arrays.asList((Studant) person)).orElseThrow(() -> new NotContentException("Não existe tccs para esse usuario"));
    }

    public void save(TCCDTO tccdto) {
        UserDetailSecurity user = UserServices.athenticated();
        Professor person = (Professor) personService.getById(user.getId());

        if (Objects.isNull(user) && (!user.hasRole(PerfilEnum.PROFESSOR) || !user.hasRole(PerfilEnum.ADMIN))) {
            throw new AuthorizationException("Usuario não logado");
        }

        TCC tcc = tccMapper.toEntity(tccdto);
        List<Studant> studants = personService.getAllStudantsIn(tccdto.getStudants());
        tcc.setStudants(studants);

        tcc.setCreatedDate(LocalDate.now());
        tcc.setModifiedDate(LocalDate.now());
        tcc.setModifiedBy(user.getUsername());
        tcc.setProfessor(person);
        TCC finalTcc = tccRepository.save(tcc);
        ;

        studants.stream().forEach(studant -> {
            studant.setTcc(finalTcc);
        });

        personService.saveAllStudants(studants);

    }
}
