package br.com.projeto.LDS.domains.mappers;

import br.com.projeto.LDS.domains.DTO.TCCDTO;
import br.com.projeto.LDS.domains.DTO.response.tcc.FilesResponse;
import br.com.projeto.LDS.domains.DTO.response.tcc.PersonTccResponse;
import br.com.projeto.LDS.domains.DTO.response.tcc.TccResponse;
import br.com.projeto.LDS.domains.entities.AcceptedFile;
import br.com.projeto.LDS.domains.entities.Studant;
import br.com.projeto.LDS.domains.entities.TCC;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TccMapper {


    public TccResponse toResponse(TCC entity) {
        return TccResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .subject(entity.getSubjects())
                .files(entity.getFiles().stream().map(this::toFileResponse).collect(Collectors.toList()))
                .studants(entity.getStudants().stream().map(this::toResponseStudant).collect(Collectors.toList()))
                .build();
    }

    private FilesResponse toFileResponse(AcceptedFile acceptedFile) {
        return FilesResponse.builder()
                .id(acceptedFile.getId())
                .TccName(acceptedFile.getFileName())
                .fileTipe(acceptedFile.getFileType())
                .url(URI.create(acceptedFile.getUrl()))
                .whoModified(acceptedFile.getModifiedBy())
                .lastModified(acceptedFile.getModifiedDate())
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
