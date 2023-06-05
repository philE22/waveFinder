package com.surfwave.waveFinder.domain.waveChart.repository;

import com.surfwave.waveFinder.domain.waveChart.entity.ChartImageJP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface JPChartImageRepository extends JpaRepository<ChartImageJP, Long> {

    boolean existsByRegionAndImageDate(String region, LocalDateTime imageDate);
}
