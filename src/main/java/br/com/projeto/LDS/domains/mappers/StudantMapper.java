package br.com.projeto.LDS.domains.mappers;

import br.com.projeto.LDS.domains.DTO.StudantDTO;
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
                .name(dto.getFirstName())
                .lastName(dto.getLastName())
                .cpf(dto.getCpf())
                .personType(dto.getPersonType())
                .build();
    }
}
