package com.surfwave.waveFinder.domain.waveChart.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PageDto {

    private Integer year;
    private Integer month;
    private Integer day;

    public PageDto(LocalDateTime localDateTime) {
        this.year = localDateTime.getYear();
        this.month = localDateTime.getMonthValue();
        this.day = localDateTime.getDayOfMonth();
    }
}
