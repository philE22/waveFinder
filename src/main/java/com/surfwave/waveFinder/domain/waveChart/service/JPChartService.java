package com.surfwave.waveFinder.domain.waveChart.service;

import com.surfwave.waveFinder.domain.waveChart.dto.JPChartDto;
import com.surfwave.waveFinder.domain.waveChart.entity.ChartImageJP;
import com.surfwave.waveFinder.domain.waveChart.repository.JPChartImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JPChartService {

    private final JPChartImageRepository repository;

    public List<JPChartDto> getDayChart(Integer year, Integer month, Integer day) {
        LocalDateTime start = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, month, day, 23, 59, 59);

        return repository.findByImageDateBetween(start, end)
                .stream()
                .map(ChartImageJP::toDto)
                .collect(Collectors.toList());
    }

}
