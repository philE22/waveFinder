package com.surfwave.waveFinder.domain.waveChart.service;

import com.surfwave.waveFinder.domain.waveChart.dto.JPChartDto;
import com.surfwave.waveFinder.domain.waveChart.entity.ChartImageJP;
import com.surfwave.waveFinder.domain.waveChart.repository.JPChartImageRepository;
import com.surfwave.waveFinder.global.file.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class AutoUploadService {

    private final JPWebCrawler crawler;
    private final S3Service s3Service;
    private final JPChartImageRepository jpChartImageRepository;

    //1, 7, 13, 19시 (6시간 간격으로)에 업데이트가 되고 새로운 차트가 2개씩 갱신되는 형태이므로 앞의 2개 이미지만 저장하면 됨
    @Scheduled(cron = "0 30 1,7,13,19 * * ?")
    public void autoSaveJP() {
        log.info("JP 자동 이미지 저장 실행. 시간={}", LocalDateTime.now());
        List<JPChartDto> jpList = crawler.getJpWaveChart("jp");

        //TODO 마지막에 저장된 이미지 날짜가 앞의 2개 이미지와 같은지 확인하는 로직 추가해야함
        //확인 로직과 저장 로직을 메서드 분리하기
        for (int i = 0; i < 2; i++) {
            JPChartDto jpChartDto = jpList.get(i);
            if (!isExist(jpChartDto)) {
                log.info("{}번 이미지 저장 시작", i);
                save(jpChartDto);
            }
        }
    }

    @Scheduled(cron = "0 30 1,7,13,19 * * ?")
    public void autoSaveSJP() {
        log.info("SJP 자동 이미지 저장 실행. 시간={}", LocalDateTime.now());
        List<JPChartDto> sjpList = crawler.getJpWaveChart("sjp");

        for (int i = 0; i < 2; i++) {
            JPChartDto jpChartDto = sjpList.get(i);
            if (!isExist(jpChartDto)) {
                log.info("{}번 이미지 저장 시작", i);
                save(jpChartDto);
            }
        }
    }

    private void save(JPChartDto jpChartDto) {
        log.info("저장 로직 시작");
        String region = jpChartDto.getRegion();
        String imagePath = jpChartDto.getImagePath();
        String fileName = generateFileName();

        String savedPath = s3Service.upload(imagePath, region, fileName);
        log.info("이미지 저장 완료 savedPath={}", savedPath);

        ChartImageJP chartImageJP = jpChartImageRepository.save(jpChartDto.toEntity(savedPath));
        log.info("jpChartImage 엔티티 저장 완료 id={}, imageDate={}, createdAt={}", chartImageJP.getId(), chartImageJP.getImageDate(), chartImageJP.getCreatedAt());
    }

    private boolean isExist(JPChartDto jpChartDto) {
        return jpChartImageRepository.existsByRegionAndImageDate(jpChartDto.getRegion(), jpChartDto.getImageDate());
    }

    private String generateFileName() {
        String fileName = UUID.randomUUID().toString() + ".png";
        log.info("랜덤 fileName이 생성되었습니다={}", fileName);
        return fileName;
    }
}
