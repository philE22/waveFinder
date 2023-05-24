package com.surfwave.waveFinder.domain.waveChart.controller;

import com.surfwave.waveFinder.domain.waveChart.dto.KRChartDto;
import com.surfwave.waveFinder.domain.waveChart.service.KROpenAPIManager;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/surf/ko")
@RequiredArgsConstructor
public class KRViewController {
    private final KROpenAPIManager apiManager;

    @GetMapping
    public String apiView(@RequestParam String region, Model model) {
        List<KRChartDto> chartDtos = apiManager.getKRWaveChartList(region.toUpperCase());
        model.addAttribute("chartDtos", chartDtos);

        return "surf-image";
    }
}
