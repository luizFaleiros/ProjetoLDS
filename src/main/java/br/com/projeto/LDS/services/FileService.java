package br.com.projeto.LDS.services;

import br.com.projeto.LDS.config.security.UserDetailSecurity;
import br.com.projeto.LDS.domains.entities.AcceptedFile;
import br.com.projeto.LDS.domains.entities.TCC;
import br.com.projeto.LDS.domains.mappers.AcceptedFileMapper;
import br.com.projeto.LDS.enums.AcceptedFileTipeEnum;
import br.com.projeto.LDS.exceptions.AuthorizationException;
import br.com.projeto.LDS.exceptions.FileException;
import br.com.projeto.LDS.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final S3Service s3Service;

    public AcceptedFileTipeEnum getExtensionName(MultipartFile multipartFile) {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (Objects.isNull(extension)) {
            throw new RuntimeException("Nome do arquivo errado");
        }
        return AcceptedFileTipeEnum.toEnumByDescription(extension.toUpperCase());

    }

    public AcceptedFile saveFile(MultipartFile multipartFile, TCC tcc) {

        UserDetailSecurity user = UserServices.athenticated();
        Optional.ofNullable(user).orElseThrow(() -> new AuthorizationException("Acesso negado"));
        AcceptedFile file = uploadFile(multipartFile);
        LocalDate today = LocalDate.now();
        file.setTcc(tcc);
        file.setCreatedDate(today);
        file.setModifiedDate(today);
        file.setModifiedBy(user.getUsername());
        return fileRepository.save(file);

    }

    public AcceptedFile uploadFile(MultipartFile multipartFile) {

        try {

            AcceptedFileTipeEnum acceptedFileTipe = getExtensionName(multipartFile);
            String fileName = multipartFile.getName()+"-"+LocalDate.now().toString()+"."+acceptedFileTipe.getDescription().toLowerCase();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            URI uri = s3Service.uploadFile(is, fileName, contentType);
            return AcceptedFileMapper.createEnity(fileName,acceptedFileTipe,uri);

        } catch (IOException e) {
            throw new FileException("Erro ao enviar arquivo");
        }
    }
}
