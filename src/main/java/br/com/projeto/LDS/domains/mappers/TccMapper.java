package br.com.projeto.LDS.domains.mappers;

import br.com.projeto.LDS.domains.DTO.TCCDTO;
import br.com.projeto.LDS.domains.DTO.response.tcc.PersonTccResponse;
import br.com.projeto.LDS.domains.DTO.response.tcc.TccResponse;
import br.com.projeto.LDS.domains.entities.Studant;
import br.com.projeto.LDS.domains.entities.TCC;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TccMapper {


    public TccResponse toResponse(TCC entity) {
        return TccResponse.builder()
                .name(entity.getName())
                .subject(entity.getSubjects())
                .studants(entity.getStudants().stream().map(this::toResponseStudant).collect(Collectors.toList()))
                .build();
    }

    private PersonTccResponse toResponseStudant(Studant entity) {
        return Optional.ofNullable(entity).map(
                studant -> PersonTccResponse.builder()
                        .id(entity.getId())
                        .lastName(entity.getLastName())
                        .firstName(entity.getName())
                        .personType(entity.getPersonType())
                        .email(entity.getEmail())
                        .build()
        ).orElse(null);
    }


    public TCC toEntity(TCCDTO entity) {
        return TCC.builder()
                .name(entity.getName())
                .subjects(entity.getSubjects())
                .build();
    }
}
