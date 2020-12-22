package br.com.projeto.LDS.domains.mappers;

import br.com.projeto.LDS.domains.DTO.StudantDTO;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.entities.Studant;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
public class StudantMapper {


    StudantDTO toDto(Studant entity) {
        return StudantDTO.builder()
                .tcc(entity.getTcc())
                .firstName(entity.getName())
                .lastName(entity.getLastName())
                .cpf(entity.getCpf())
                .personType(entity.getPersonType())
                .build();
    }

    public Studant toEntity(StudantDTO dto){
        return Studant.builder()
                .tcc(dto.getTcc())
                .cpf(dto.getCpf())
                .lastName(dto.getLastName())
                .name(dto.getFirstName())
                .code(dto.getCode())
                .build();
    }

    public Person updateEntity(Studant p, StudantDTO person) {
        p.setCode(person.getCode());
        p.setTcc(person.getTcc());
        return p;
    }
}
