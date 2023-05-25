package com.surfwave.waveFinder.domain.waveChart.repository;

import com.surfwave.waveFinder.domain.waveChart.entity.ChartImageJP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KRChartImageRepository extends JpaRepository<ChartImageJP, Long> {
}
