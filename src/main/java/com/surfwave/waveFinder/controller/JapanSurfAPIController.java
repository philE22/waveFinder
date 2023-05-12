package com.surfwave.waveFinder.controller;

import com.surfwave.waveFinder.domain.api.JapanInfo;
import com.surfwave.waveFinder.service.JapanWebCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/surf/jp")
@RequiredArgsConstructor
public class JapanSurfAPIController {

    private final JapanWebCrawler crawler;

    @GetMapping
    public String apiView(Model model) throws IOException {

        List<JapanInfo> data = crawler.crawling();

        model.addAttribute("data", data);
        return "surf-image";
    }
}
