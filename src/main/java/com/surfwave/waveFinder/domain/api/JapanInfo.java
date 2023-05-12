package com.surfwave.waveFinder.domain.api;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class JapanInfo {
    private LocalDateTime localDateTime;
    private Integer day;
    private Integer hour;
    private String dayOfWeek;
    private String imagePath;
}
