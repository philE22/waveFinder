package com.surfwave.waveFinder.controller;

import com.surfwave.waveFinder.domain.api.SeaForecastInfo;
import com.surfwave.waveFinder.service.OpenAPIManager;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/surf/ko")
@RequiredArgsConstructor
public class KoreaSurfAPIController {
    private final String SOKCHO = "SOKCHO";
    private final String BUSAN = "BUSAN";
    private final String TAEAN = "TAEAN";
    private final String JEJU = "JEJU";

    private final OpenAPIManager apiManager;


    @GetMapping("/{type}")
    public String apiView(@PathVariable String type, Model model) throws ParseException {
        List<SeaForecastInfo> data = apiManager.getData(type.toUpperCase());
        model.addAttribute("data", data);

        return "/surf-image";
    }
}
