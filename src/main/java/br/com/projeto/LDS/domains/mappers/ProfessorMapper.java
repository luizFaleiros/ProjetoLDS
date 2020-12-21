package br.com.projeto.LDS.domains.mappers;

import br.com.projeto.LDS.domains.DTO.ProfessorDTO;
import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.entities.TCC;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class ProfessorMapper {

    ProfessorDTO toDto(Professor entity) {
        return ProfessorDTO.builder()
                .cpf(entity.getCpf())
                .firstName(entity.getName())
                .personType(entity.getPersonType())
                .lastName(entity.getLastName())
                .build();
    }

    public Professor toEntity(ProfessorDTO dto) {
        return Professor.builder()
                .tcc(new HashSet<>(dto.getTccs()))
                .name(dto.getFirstName())
                .lastName(dto.getLastName())
                .cpf(dto.getCpf())
                .personType(dto.getPersonType())
                .build();
    }
}
