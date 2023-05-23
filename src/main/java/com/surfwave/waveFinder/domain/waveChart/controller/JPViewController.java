package com.surfwave.waveFinder.domain.waveChart.controller;

import com.surfwave.waveFinder.domain.waveChart.entity.JPChart;
import com.surfwave.waveFinder.domain.waveChart.service.JPWebCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/surf/jp")
@RequiredArgsConstructor
public class JPViewController {

    private final JPWebCrawler crawler;

    @GetMapping
    public String apiView(@RequestParam("type") String type, Model model) {

        List<JPChart> data = crawler.getJpWaveChart(type);
        model.addAttribute("data", data);
        return "surf-image";
    }
}
