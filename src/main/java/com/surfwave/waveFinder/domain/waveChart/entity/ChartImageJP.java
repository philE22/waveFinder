package com.surfwave.waveFinder.domain.waveChart.entity;

import com.surfwave.waveFinder.domain.waveChart.dto.JPChartDto;
import com.surfwave.waveFinder.global.auditable.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "chart_image_jp")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChartImageJP extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String region;
    @Column
    private String savedPath;
    @Column
    private LocalDateTime imageDate;

    @Builder
    public ChartImageJP(String region, String savedPath, LocalDateTime imageDate) {
        this.region = region;
        this.savedPath = savedPath;
        this.imageDate = imageDate;
    }

    public JPChartDto toDto() {
        return new JPChartDto(this.region, this.savedPath, this.imageDate);
    }
}
