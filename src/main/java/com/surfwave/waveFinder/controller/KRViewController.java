package com.surfwave.waveFinder.controller;

import com.surfwave.waveFinder.domain.api.KRWaveChart;
import com.surfwave.waveFinder.service.OpenAPIManager;
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
    private final OpenAPIManager apiManager;

    @GetMapping
    public String apiView(@RequestParam String type, Model model) throws ParseException {
        List<KRWaveChart> data = apiManager.getKRWaveChartList(type.toUpperCase());
        model.addAttribute("data", data);

        return "surf-image";
    }
}
