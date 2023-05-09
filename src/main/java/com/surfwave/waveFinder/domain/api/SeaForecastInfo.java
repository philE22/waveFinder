package com.surfwave.waveFinder.domain.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SeaForecastInfo {
    private String name;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private String dayOfWeek;
    private String filePath;
}
