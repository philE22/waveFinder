package com.surfwave.waveFinder.domain.waveChart.service;

import com.surfwave.waveFinder.domain.waveChart.dto.JPChartDto;
import com.surfwave.waveFinder.domain.waveChart.entity.ChartImageJP;
import com.surfwave.waveFinder.domain.waveChart.repository.JPChartImageRepository;
import com.surfwave.waveFinder.global.file.S3Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

//@RunWith(MockitoJUnitRunner.class)
@Transactional
@SpringBootTest
class AutoUploadServiceTest {

    @Autowired
    private JPChartImageRepository repository;

    @Autowired
    private AutoUploadService uploadService;

    @MockBean
    private JPWebCrawler crawler;

    @MockBean
    private S3Service s3Service;

    @BeforeEach
    void before() {
        //6월 1일 0, 3, 6, 9, 12, 15, 18, 21시 이미지를 미리 저장
        LocalDateTime time = LocalDateTime.of(2023, 6, 1, 0, 0);

        for (int i = 1; i <= 8; i++) {
            ChartImageJP jp = ChartImageJP.builder()
                    .region("jp")
                    .savedPath("/path/" + i)
                    .imageDate(time)
                    .build();
            repository.save(jp);
            time = time.plusHours(3);
        }
    }

    @Test
    @DisplayName("저장이 제대로 될 경우 테스트")
    void save() {
        //given
        ArrayList<JPChartDto> jpList = new ArrayList<>();
        LocalDateTime time = LocalDateTime.of(2023, 6, 2, 0, 0);
        JPChartDto jp1 = new JPChartDto("jp", "/path/crawling/1", time);
        JPChartDto jp2 = new JPChartDto("jp", "/path/crawling/2", time.plusHours(3));
        jpList.add(jp1);
        jpList.add(jp2);

        given(crawler.getJpWaveChart("jp")).willReturn(jpList);
        given(s3Service.upload(anyString(), anyString(), anyString())).willReturn("저장된 이미지 경로");

        //when
        uploadService.autoSaveJP();

        //then
        assertThat(repository.findAll().size()).isEqualTo(10);
        assertThat(repository.existsByRegionAndImageDate("jp", time)).isTrue();
        assertThat(repository.existsByRegionAndImageDate("jp", time.plusHours(3))).isTrue();

    }
    @Test
    @DisplayName("이미 저장된 이미지는 저장하지 않음")
    void savefail() {
        //given
        ArrayList<JPChartDto> jpList = new ArrayList<>();
        LocalDateTime time = LocalDateTime.of(2023, 6, 1, 18, 0);
        JPChartDto jp1 = new JPChartDto("jp", "/path/crawling/1", time);
        JPChartDto jp2 = new JPChartDto("jp", "/path/crawling/2", time.plusHours(3));
        jpList.add(jp1);
        jpList.add(jp2);

        given(crawler.getJpWaveChart("jp")).willReturn(jpList);
        given(s3Service.upload(anyString(), anyString(), anyString())).willReturn("저장된 이미지 경로");

        //when
        uploadService.autoSaveJP();

        //then
        assertThat(repository.findAll().size()).isEqualTo(8);
    }
}