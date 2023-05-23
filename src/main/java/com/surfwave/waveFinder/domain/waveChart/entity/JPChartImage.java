package com.surfwave.waveFinder.domain.waveChart.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JPChartImage {
    @Id
    private Long id;
    @Column
    private String region;
    @Column
    private String savedPath;
    @Column
    private LocalDateTime imageDate;

    @Builder
    public JPChartImage(String region, String savedPath, LocalDateTime imageDate) {
        this.region = region;
        this.savedPath = savedPath;
        this.imageDate = imageDate;
    }
}
