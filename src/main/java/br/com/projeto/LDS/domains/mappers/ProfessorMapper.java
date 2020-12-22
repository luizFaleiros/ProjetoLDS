package br.com.projeto.LDS.domains.mappers;

import br.com.projeto.LDS.domains.DTO.ProfessorDTO;
import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.entities.TCC;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
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
        Set<TCC> tccs = new HashSet<>();
        return Professor.builder()
                .tcc(ListUtils.emptyIfNull(dto.getTccs()).stream().collect(Collectors.toSet()))
                .cpf(dto.getCpf())
                .lastName(dto.getLastName())
                .personType(dto.getPersonType())
                .name(dto.getFirstName())
                .build();
    }
}
