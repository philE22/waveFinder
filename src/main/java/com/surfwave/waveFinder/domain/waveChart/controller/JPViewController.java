package com.surfwave.waveFinder.domain.waveChart.controller;

import com.surfwave.waveFinder.domain.waveChart.dto.JPChartDto;
import com.surfwave.waveFinder.domain.waveChart.dto.PageDto;
import com.surfwave.waveFinder.domain.waveChart.entity.ChartImageJP;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/surf/jp")
@RequiredArgsConstructor
public class JPViewController {

    private final JPWebCrawler crawler;
    private final JPChartService service;
    private static final LocalDateTime start = LocalDateTime.of(2023, 5, 26, 0, 0);


    @GetMapping
    public String apiView(@RequestParam String region, Model model) {
        List<JPChartDto> chartDtos = crawler.getJpWaveChart(region);
        model.addAttribute("chartDtos", chartDtos);
        return "surf-image-range";
    }

    @GetMapping
    @RequestMapping("/list")
    public String dayList(@RequestParam Integer page, Model model) {
        LocalDateTime localDateTime = LocalDateTime.now();

        ArrayList<PageDto> pageDtos = new ArrayList<>();
        while (localDateTime.isAfter(start)) {
            localDateTime = localDateTime.minusDays(1);
            pageDtos.add(new PageDto(localDateTime));
        }
        model.addAttribute("pageDtos", pageDtos);

        //5월 26일부터 자료 있음

        return "surf-chart-list";
    }

    @GetMapping("/day")
    public String dayChart(@RequestParam Integer year,
                           @RequestParam Integer month,
                           @RequestParam Integer day, Model model) {

        List<JPChartDto> dayChartList = service.getDayChart(year, month, day);
        model.addAttribute("chartDtos", dayChartList);
        return "surf-image-range";
    }
}
