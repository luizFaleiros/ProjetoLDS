package br.com.projeto.LDS.domains.mappers;

import br.com.projeto.LDS.domains.entities.AcceptedFile;
import br.com.projeto.LDS.enums.AcceptedFileTipeEnum;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class AcceptedFileMapper {
    public static AcceptedFile createEnity(String fileName, AcceptedFileTipeEnum acceptedFileTipe, URI uri) {
        return AcceptedFile.builder()
                .fileType(acceptedFileTipe)
                .url(uri.toString())
                .fileName(fileName)
                .build();
    }
}
