package com.example.Natours.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.synth.Region;
import java.io.IOException;

@Service
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3Service(
            @Value("${aws.accessKey}") String accessKey,
            @Value("${aws.secretKey}") String secretKey,
            @Value("${aws.region}") String region) {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);

        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(bucketName, fileName, file.getInputStream(), null);
        return s3Client.getUrl(bucketName, fileName).toString();
    }

}

