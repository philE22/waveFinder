package com.surfwave.waveFinder.domain.waveChart.repository;

import com.surfwave.waveFinder.domain.waveChart.entity.ChartImageJP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JPChartImageRepository extends JpaRepository<ChartImageJP, Long> {

    boolean existsByRegionAndImageDate(String region, LocalDateTime imageDate);

    List<ChartImageJP> findByRegionAndImageDateBetween(String region, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
