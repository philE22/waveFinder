package com.surfwave.waveFinder.global.config;

import com.amazonaws.services.s3.AmazonS3Client;
import com.surfwave.waveFinder.global.file.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

@Slf4j
@SpringBootTest
class S3ServiceTest {

    @Autowired
    private S3Service service;
    @Autowired
    private AmazonS3Client amazonS3Client;


    /**
     * s3 업로드 관련해서 기능들 테스트 해보면서 2번 업로드 되는 이유 찾기
     * 안되면 추적하기 위한 로그 남기기
     * 5.30일 19:30 6.8일 13:30 두번 저장됨
     * <p>
     * s3에 테스트 dir 만들어서 테스트 하기
     * result.getEtag 테스트해서 뭔지 알아보기
     */
    @Test
    void upload() {
        String imagePath = "https://www.imocwx.com/cwm/cwmsjp_24.png?1206";
        String dir = "test";
        String fileName = "441111444.png";

        String upload = service.upload(imagePath, dir, fileName);

        System.out.println(upload);
    }

    @Test
    void getUrl() {
        String bucketName = "wavefinder-bucket";
        String dir = "test";
        String fileName = "testImage11";

        URL url = amazonS3Client.getUrl(bucketName, dir + "/" + fileName);
        System.out.println(url.toString());
    }
}