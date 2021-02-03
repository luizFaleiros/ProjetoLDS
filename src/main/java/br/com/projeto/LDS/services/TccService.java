package br.com.projeto.LDS.services;

import br.com.projeto.LDS.config.security.UserDetailSecurity;
import br.com.projeto.LDS.domains.DTO.TCCDTO;
import br.com.projeto.LDS.domains.DTO.filter.params.TccFilter;
import br.com.projeto.LDS.domains.entities.AcceptedFile;
import br.com.projeto.LDS.domains.entities.BaseEntity;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.entities.Studant;
import br.com.projeto.LDS.domains.entities.TCC;
import br.com.projeto.LDS.domains.mappers.TccMapper;
import br.com.projeto.LDS.enums.AcceptedFileTipeEnum;
import br.com.projeto.LDS.enums.PerfilEnum;
import br.com.projeto.LDS.exceptions.AuthorizationException;
import br.com.projeto.LDS.exceptions.NotContentException;
import br.com.projeto.LDS.exceptions.NotFoundException;
import br.com.projeto.LDS.repositories.TccRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TccService {
    private final TccRepository tccRepository;
    private final PersonService personService;
    private final TccMapper tccMapper;
    private final FileService fileService;


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

    public URI uploadTccFile(MultipartFile multipartFile, Long id) {
        UserDetailSecurity user = UserServices.athenticated();
        Optional.ofNullable(user).orElseThrow(() -> new AuthorizationException("Acesso negado"));
        TCC tcc = findById(id);
        AcceptedFile file = fileService.saveFile(multipartFile, tcc);
        return URI.create(file.getUrl());
    }

    public TCC findById(Long id) {
        UserDetailSecurity user = UserServices.athenticated();
        Long userId = user.getId();
        Optional.ofNullable(user).orElseThrow(() -> new AuthorizationException("Acesso negado"));
        TCC tcc = tccRepository.findById(id).orElseThrow(() -> new NotFoundException("TCC não encontrado"));
        Long studantId = tcc.getStudants().stream().filter(studant -> studant.getId() == userId).findAny().map(BaseEntity::getId).orElse(null);
        boolean isTccProfessor = userId.equals(tcc.getProfessor().getId());
        if ( isTccProfessor || Objects.nonNull(studantId)) {
            return tcc;
        }
        throw new NotFoundException("Tcc não pertence a essa pessoa");

    }


}
