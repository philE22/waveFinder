package com.surfwave.waveFinder.domain.waveChart.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KRChart {
    private String name;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private String dayOfWeek;
    private String imagePath;
}
