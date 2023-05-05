package com.surfwave.waveFinder.controller;

import com.google.gson.Gson;
import com.surfwave.waveFinder.Info;
import com.surfwave.waveFinder.SurfInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/surf")
@RequiredArgsConstructor
public class KoreaSurfAPIController {

    RestTemplate restTemplate = new RestTemplate();
    Gson gson = new Gson();
    private final String URL = "http://www.khoa.go.kr/api/oceangrid/preOcean/search.do?ResultType=json";
    @Value("${serviceKey}")
    private String serviceKey;
    private final String SOKCHO = "&type=SOKCHO";
    private final String BUSAN = "&type=BUSAN";
    private final String TAEAN = "&type=TAEAN";
    private final String JEJU = "&type=JEJU";


    @GetMapping("/sokcho")
    public String sokcho(Model model) {
        System.out.println(URL + SOKCHO + serviceKey);
        String json = restTemplate.getForObject(URL + SOKCHO + serviceKey, String.class);
        Info info = gson.fromJson(json, Info.class);
        List<SurfInfo> data = info.getResult().getData();
        model.addAttribute("data", data);

        return "/surf-image";
    }
    @GetMapping("/busan")
    public String busan(Model model) {
        String json = restTemplate.getForObject(URL + BUSAN + serviceKey, String.class);
        Info info = gson.fromJson(json, Info.class);
        List<SurfInfo> data = info.getResult().getData();
        model.addAttribute("data", data);

        return "/surf";
    }
    @GetMapping("/jeju")
    public String jeju(Model model) {
        String json = restTemplate.getForObject(URL + JEJU + serviceKey, String.class);
        Info info = gson.fromJson(json, Info.class);
        List<SurfInfo> data = info.getResult().getData();
        model.addAttribute("data", data);

        return "/surf";
    }
    @GetMapping("/taean")
    public String taean(Model model) {
        String json = restTemplate.getForObject(URL + TAEAN + serviceKey, String.class);
        Info info = gson.fromJson(json, Info.class);
        List<SurfInfo> data = info.getResult().getData();
        model.addAttribute("data", data);

        return "/surf";
    }
}
