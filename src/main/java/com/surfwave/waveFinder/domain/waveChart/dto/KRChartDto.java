package com.surfwave.waveFinder.domain.waveChart.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter
public class KRChartDto {

    private String region;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private String dayOfWeek;
    //"yyyyMMddHH" 형태의 문자열
    private String dateTimeString;
    private String imagePath;
    private LocalDateTime imageDate;

    public KRChartDto(String region, String imagePath, LocalDateTime imageDate) {
        this.region = region;
        this.imagePath = imagePath;
        this.imageDate = imageDate;

        //타임리프에서 사용을 위해 등록
        this.year = imageDate.getYear();
        this.month = imageDate.getMonthValue();
        this.day = imageDate.getDayOfMonth();
        this.hour = imageDate.getHour();
        this.dayOfWeek = imageDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA);
        this.dateTimeString = imageDate.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
    }
}
