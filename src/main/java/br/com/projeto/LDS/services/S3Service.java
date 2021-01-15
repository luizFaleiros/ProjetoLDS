package br.com.projeto.LDS.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    private Logger log = LoggerFactory.getLogger(S3Service.class);

    public URI uploadFile(MultipartFile multipartFile) throws IOException {

        String fileName = multipartFile.getOriginalFilename();
        InputStream is = Optional.of(multipartFile.getInputStream()).orElseThrow(IOException::new);
        String contentType = multipartFile.getContentType();
        log.info("Iniciando o upload");
        return uploadFile(is, fileName, contentType);

    }

    public URI uploadFile(InputStream is, String fileName, String contentyType) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();

            File file = new File(localFilePath);
            log.info("Iniciando o upload");
            s3Client.putObject(new PutObjectRequest(bucketName, "teste", file));
        } catch (AmazonServiceException e) {
            log.info("AmazonServiceException: " + e.getErrorMessage());
            log.info("AmazonServiceException: " + e.getErrorCode());
        } catch (AmazonClientException e) {
            log.info("AmazonClientException: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
