package com.surfwave.waveFinder.global.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    private final AmazonS3Client amazonS3Client;

    public String upload(String imagePath, String dir, String fileName) {
        byte[] imageData = null;

        try {
            URL url = new URL(imagePath);
            InputStream inputStream = url.openStream();
            imageData = StreamUtils.copyToByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("이미지를 크롤링하기 위한 URL 이 존재하지 않습니다.", e);
        }

        InputStream inputStream = new ByteArrayInputStream(imageData);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(MediaType.IMAGE_PNG_VALUE);
        objectMetadata.setContentLength(imageData.length);

        amazonS3Client.putObject(bucketName, dir + "/" + fileName, inputStream, objectMetadata);
        return amazonS3Client.getUrl(bucketName, dir + "/" + fileName).toString();
    }
}
