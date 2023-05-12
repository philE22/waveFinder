package com.surfwave.waveFinder.domain.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JPWaveChart {
    private Integer day;
    private Integer hour;
    private String dayOfWeek;
    private String imagePath;
}
