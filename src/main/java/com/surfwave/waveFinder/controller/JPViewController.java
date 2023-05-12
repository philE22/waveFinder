package com.surfwave.waveFinder.controller;

import com.surfwave.waveFinder.domain.api.JPWaveChart;
import com.surfwave.waveFinder.service.JapanWebCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/surf/jp")
@RequiredArgsConstructor
public class JPViewController {

    private final JapanWebCrawler crawler;

    @GetMapping
    public String apiView(@RequestParam("type") String type, Model model) throws IOException {

        List<JPWaveChart> data = crawler.getJpWaveChart(type);
        model.addAttribute("data", data);
        return "surf-image";
    }
}
