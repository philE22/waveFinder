package com.surfwave.waveFinder.domain.waveChart.repository;

import com.surfwave.waveFinder.domain.waveChart.entity.JPChartImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KRChartImageRepository extends JpaRepository<JPChartImage, Long> {
}
