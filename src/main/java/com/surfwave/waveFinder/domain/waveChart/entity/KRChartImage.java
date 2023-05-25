package com.surfwave.waveFinder.domain.waveChart.entity;

import com.surfwave.waveFinder.global.auditable.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KRChartImage extends Auditable {
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
    public KRChartImage(String region, String savedPath, LocalDateTime imageDate) {
        this.region = region;
        this.savedPath = savedPath;
        this.imageDate = imageDate;
    }
}
