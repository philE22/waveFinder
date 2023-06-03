package com.surfwave.waveFinder.domain.waveChart.controller;

import com.surfwave.waveFinder.domain.waveChart.dto.JPChartDto;
import com.surfwave.waveFinder.domain.waveChart.service.JPWebCrawler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/surf/jp")
@RequiredArgsConstructor
public class JPViewController {

    private final JPWebCrawler crawler;

    @GetMapping
    public String apiView(@RequestParam String region, Model model) {
        List<JPChartDto> chartDtos = crawler.getJpWaveChart(region);
        model.addAttribute("chartDtos", chartDtos);
        return "surf-image-range";
    }
}
