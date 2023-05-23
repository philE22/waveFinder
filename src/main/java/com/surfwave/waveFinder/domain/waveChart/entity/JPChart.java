package com.surfwave.waveFinder.domain.waveChart.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JPChart {
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private String dayOfWeek;
    private String imagePath;
    private String fullDateTime;
}
