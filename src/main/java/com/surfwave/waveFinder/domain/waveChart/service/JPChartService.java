package com.surfwave.waveFinder.domain.waveChart.service;

import com.surfwave.waveFinder.domain.waveChart.dto.DayDto;
import com.surfwave.waveFinder.domain.waveChart.dto.JPChartDto;
import com.surfwave.waveFinder.domain.waveChart.entity.ChartImageJP;
import com.surfwave.waveFinder.domain.waveChart.repository.JPChartImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JPChartService {

    private final JPChartImageRepository repository;

    /**
     * 5월 26일부터 데이터 있음
     */
    private final LocalDateTime START = LocalDateTime.of(2023, 5, 26, 0, 0, 0);

    public List<JPChartDto> getDayChart(String region, Integer year, Integer month, Integer day) {
        LocalDateTime start = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, month, day, 23, 59, 59);

        return repository.findByRegionAndImageDateBetween(region, start, end)
                .stream()
                .map(ChartImageJP::toDto)
                .collect(Collectors.toList());
    }

    public List<DayDto> getDayDto(LocalDateTime now) {
        ArrayList<DayDto> dayDtos = new ArrayList<>();

        //당일의 1시 반 이전인지 확인해서 맞으면 하루 빼는 로직
        //당일 1시 31분 까지는 당일 0시 차트가 저장되지 않으므로 31분을 기준으로 삼음
        LocalDateTime updateTime = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 1, 31);
        if (now.isBefore(updateTime)) {
            now = now.minusDays(1);
        }

        while (now.isAfter(START)) {
            dayDtos.add(new DayDto(now));
            now = now.minusDays(1);
        }

        return dayDtos;
    }

}
