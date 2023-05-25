package com.surfwave.waveFinder.global.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


@Service
@RequiredArgsConstructor
public class S3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    private final AmazonS3Client amazonS3Client;
    private final RestTemplate restTemplate;

    public String upload(String imagePath, String dir, String fileName) {
        byte[] imageData = null;

        try {
            imageData = restTemplate.getForObject(imagePath, byte[].class);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("이미지를 크롤링하기 위한 URL 이 존재하지 않습니다.");
        }
        InputStream inputStream = new ByteArrayInputStream(imageData);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/png");
        objectMetadata.setContentLength(imageData.length);

        amazonS3Client.putObject(bucketName, dir + "/" + fileName, inputStream, objectMetadata);
        return amazonS3Client.getUrl(bucketName, dir + "/" + fileName).toString();
    }
}
