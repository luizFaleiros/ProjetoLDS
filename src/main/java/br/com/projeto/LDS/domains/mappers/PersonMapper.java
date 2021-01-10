package br.com.projeto.LDS.domains.mappers;

import br.com.projeto.LDS.domains.DTO.PersonDTO;
import br.com.projeto.LDS.domains.DTO.ProfessorDTO;
import br.com.projeto.LDS.domains.DTO.StudantDTO;
import br.com.projeto.LDS.domains.DTO.response.person.PersonResponse;
import br.com.projeto.LDS.domains.DTO.response.person.PersonTccResponse;
import br.com.projeto.LDS.domains.DTO.response.person.ProfessorResponse;
import br.com.projeto.LDS.domains.DTO.response.person.StudantResponse;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.entities.Studant;
import br.com.projeto.LDS.domains.entities.TCC;
import br.com.projeto.LDS.enums.PerfilEnum;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public ProfessorDTO toDto(Professor entity) {
        return ProfessorDTO.builder()
                .cpf(entity.getCpf())
                .firstName(entity.getName())
                .password(entity.getPass())
                .personType(entity.getPersonType())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .build();
    }

    public Person toEntity(PersonDTO dto) {
        Person p;
        if (dto instanceof ProfessorDTO) {
            p = toEntity((ProfessorDTO) dto);
        } else {
            p = toEntity((StudantDTO) dto);
        }
        return p;
    }

    public PersonDTO toDto(Person entity) {
        PersonDTO p;
        if (entity instanceof Professor) {
            p = toDto((Professor) entity);
        } else {
            p = toDto((Studant) entity);
        }
        return p;
    }

    public Professor toEntity(ProfessorDTO dto) {
        Professor professor = Professor.builder()
                .tcc(new ArrayList<>(ListUtils.emptyIfNull(dto.getTccs())))
                .cpf(dto.getCpf())
                .lastName(dto.getLastName())
                .personType(dto.getPersonType())
                .name(dto.getFirstName())
                .pass(dto.getPassword())
                .email(dto.getEmail())
                .build();
        professor.addPerfil(PerfilEnum.PROFESSOR);
        return professor;
    }

    public Professor updateEntity(Professor p, Professor person) {
        p.setTcc(new ArrayList<>(ListUtils.emptyIfNull(person.getTcc())));
        return p;
    }


    public StudantDTO toDto(Studant entity) {
        return StudantDTO.builder()
                .tcc(entity.getTcc())
                .firstName(entity.getName())
                .lastName(entity.getLastName())
                .cpf(entity.getCpf())
                .personType(entity.getPersonType())
                .password(entity.getPass())
                .build();
    }

    public Studant toEntity(StudantDTO dto) {
        Studant studant = Studant.builder()
                .tcc(dto.getTcc())
                .cpf(dto.getCpf())
                .lastName(dto.getLastName())
                .name(dto.getFirstName())
                .code(dto.getCode())
                .pass(dto.getPassword())
                .email(dto.getEmail())
                .personType(dto.getPersonType())
                .build();
        studant.addPerfil(PerfilEnum.STUDANT);
        return studant;
    }

    public Person updateEntity(Studant p, Studant person) {
        p.setCode(person.getCode());
        p.setTcc(person.getTcc());
        return p;
    }

    public Person updateEntity(Person atual, Person update) {
        Person p;
        if (atual instanceof Professor && update instanceof Professor) {
            p = updateEntity((Professor) atual, (Professor) update);
        } else if (atual instanceof Studant && update instanceof Studant) {
            p = updateEntity((Studant) atual, (Studant) update);
        } else {
            p = atual;
        }
        return p;
    }

    public PersonResponse toResponse(Person entity) {

        if (entity instanceof Professor) {
            return toResponse((Professor) entity);
        } else {
            return toResponse((Studant) entity);
        }
    }

    public ProfessorResponse toResponse(Professor entity) {
        return Optional.ofNullable(entity).map(
                professor -> ProfessorResponse.builder()
                        .id(entity.getId())
                        .firstName(entity.getName())
                        .personType(entity.getPersonType())
                        .lastName(entity.getLastName())
                        .email(entity.getEmail())
                        .tccs(entity.getTcc().stream().map(this::toTcc).collect(Collectors.toList()))
                        .build()).orElse(null);
    }

    private PersonTccResponse toTcc(TCC tcc) {
        return Optional.ofNullable(tcc).map(
                tccp -> PersonTccResponse.builder()
                .name(tcc.getName())
                .id(tcc.getId())
                .build()
        ).orElse(null);
    }


    public StudantResponse toResponse(Studant entity) {
        return StudantResponse.builder()
                .id(entity.getId())
                .tcc(toTcc(entity.getTcc()))
                .firstName(entity.getName())
                .lastName(entity.getLastName())
                .personType(entity.getPersonType())
                .build();
    }

    public Studant idToEntity(Long id) {
        return Studant.builder()
                .id(id)
                .build();

    }

}
