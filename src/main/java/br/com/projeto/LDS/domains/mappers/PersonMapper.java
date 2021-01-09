package br.com.projeto.LDS.domains.mappers;

import br.com.projeto.LDS.domains.DTO.PersonDTO;
import br.com.projeto.LDS.domains.DTO.ProfessorDTO;
import br.com.projeto.LDS.domains.DTO.StudantDTO;
import br.com.projeto.LDS.domains.DTO.response.PersonResponse;
import br.com.projeto.LDS.domains.DTO.response.ProfessorResponse;
import br.com.projeto.LDS.domains.DTO.response.StudantResponse;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.entities.Studant;
import br.com.projeto.LDS.enums.PerfilEnum;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

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
                .tcc(ListUtils.emptyIfNull(dto.getTccs()).stream().collect(Collectors.toSet()))
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

    public Professor updateEntity(Professor p, ProfessorDTO person) {
        p.setTcc(ListUtils.emptyIfNull(person.getTccs()).stream().collect(Collectors.toSet()));
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

    public Person updateEntity(Studant p, StudantDTO person) {
        p.setCode(person.getCode());
        p.setTcc(person.getTcc());
        return p;
    }

    public Person updateEntity(Person atual, PersonDTO update) {
        Person p;
        if (atual instanceof Professor && update instanceof ProfessorDTO) {
            p = updateEntity((Professor) atual, (ProfessorDTO) update);
        } else if (atual instanceof Studant && update instanceof StudantDTO) {
            p = updateEntity((Studant) atual, (StudantDTO) update);
        }else {
            p = atual;
        }
        return p;
    }

    public PersonResponse toResponse(Person entity) {
        PersonResponse p;
        if (entity instanceof Professor) {
            p = toResponse((Professor) entity);
        } else {
            p = toResponse((Studant) entity);
        }
        return p;
    }

    public ProfessorResponse toResponse(Professor entity) {
        return ProfessorResponse.builder()
                .firstName(entity.getName())
                .personType(entity.getPersonType())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .build();
    }

    public StudantResponse toResponse(Studant entity) {
        return StudantResponse.builder()
                .tcc(entity.getTcc())
                .firstName(entity.getName())
                .lastName(entity.getLastName())
                .personType(entity.getPersonType())
                .build();
    }
}
