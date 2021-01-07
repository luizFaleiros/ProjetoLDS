package br.com.projeto.LDS.domains.mappers;

import br.com.projeto.LDS.domains.DTO.PersonDTO;
import br.com.projeto.LDS.domains.DTO.ProfessorDTO;
import br.com.projeto.LDS.domains.DTO.StudantDTO;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.entities.Studant;
import br.com.projeto.LDS.domains.entities.TCC;
import br.com.projeto.LDS.enums.PerfilEnum;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

import java.util.Set;
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
                .build();
        studant.addPerfil(PerfilEnum.PARTICIPANT);
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
}
