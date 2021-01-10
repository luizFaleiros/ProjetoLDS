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

import java.io.File;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    private Logger log = LoggerFactory.getLogger(S3Service.class);

    public void uploadFile(String localFilePath) {
        try {
            File file = new File(localFilePath);
            log.info("Iniciando o upload");
            s3Client.putObject(new PutObjectRequest(bucketName, "teste", file));
        } catch (AmazonServiceException e) {
            log.info("AmazonServiceException: " + e.getErrorMessage());
            log.info("AmazonServiceException: " + e.getErrorCode());
        } catch (AmazonClientException e) {
            log.info("AmazonClientException: " + e.getMessage());
        }
    }
}
