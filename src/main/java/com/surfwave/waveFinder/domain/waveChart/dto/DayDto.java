package com.surfwave.waveFinder.domain.waveChart.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DayDto {

    private Integer year;
    private Integer month;
    private Integer day;

    public DayDto(LocalDateTime localDateTime) {
        this.year = localDateTime.getYear();
        this.month = localDateTime.getMonthValue();
        this.day = localDateTime.getDayOfMonth();
    }
}
