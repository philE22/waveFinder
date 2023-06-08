package com.surfwave.waveFinder.domain.waveChart.controller;

import com.surfwave.waveFinder.domain.waveChart.dto.JPChartDto;
import com.surfwave.waveFinder.domain.waveChart.dto.DayDto;
import com.surfwave.waveFinder.domain.waveChart.service.JPChartService;
import com.surfwave.waveFinder.domain.waveChart.service.JPWebCrawler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/surf/jp")
@RequiredArgsConstructor
public class JPViewController {

    private final JPWebCrawler crawler;
    private final JPChartService service;

    @GetMapping("/crawling")
    public String apiView(@RequestParam String region, Model model) {
        List<JPChartDto> chartDtos = crawler.getJpWaveChart(region);
        model.addAttribute("chartDtos", chartDtos);

        return "chartImagePage";
    }

    @GetMapping("/list")
    public String dayList(Model model) {
        List<DayDto> dayDtos = service.getDayDto(LocalDateTime.now());
        model.addAttribute("dayDtos", dayDtos);

        return "dayListPage";
    }

    @GetMapping("/day")
    public String dayChart(@RequestParam Integer year,
                           @RequestParam Integer month,
                           @RequestParam Integer day,
                           @RequestParam String region, Model model) {

        List<JPChartDto> chartDtos = service.getDayChart(region, year, month, day);
        model.addAttribute("chartDtos", chartDtos);

        return "chartImagePage";
    }
}
