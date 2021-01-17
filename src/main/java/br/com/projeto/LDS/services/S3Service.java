package br.com.projeto.LDS.services;

import br.com.projeto.LDS.exceptions.FileException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    private Logger log = LoggerFactory.getLogger(S3Service.class);

    public URI uploadFile(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            log.info("Iniciando o upload");
            URI uri =  uploadFile(is, fileName, contentType);
            log.info("Terminando o upload");
            return uri;
        } catch (IOException e) {
            throw new FileException("Erro ao tentar fazer upload");
        }


    }

    public URI uploadFile(InputStream is, String fileName, String contentyType) {

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentyType);
            s3Client.putObject(bucketName, fileName, is, metadata);


            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao tentar fazer upload");
        }

    }
}
