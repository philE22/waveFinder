package com.surfwave.waveFinder;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/surf")
@RequiredArgsConstructor
public class ApiController {

    RestTemplate restTemplate = new RestTemplate();
    Gson gson = new Gson();
    private final String URL = "http://www.khoa.go.kr/api/oceangrid/preOcean/search.do?ServiceKey=OUkrebOp9eqxGzHu5vA3pw==&ResultType=json";
    private final String SOKCHO = "&type=SOKCHO";
    private final String BUSAN = "&type=BUSAN";
    private final String TAEAN = "&type=TAEAN";
    private final String JEJU = "&type=JEJU";


    @GetMapping("/sokcho")
    public String sokcho(Model model) {
        String json = restTemplate.getForObject(URL + SOKCHO, String.class);
        Info info = gson.fromJson(json, Info.class);
        List<SurfInfo> data = info.getResult().getData();
        model.addAttribute("data", data);

        return "/surf";
    }
    @GetMapping("/busan")
    public String busan(Model model) {
        String json = restTemplate.getForObject(URL + BUSAN, String.class);
        Info info = gson.fromJson(json, Info.class);
        List<SurfInfo> data = info.getResult().getData();
        model.addAttribute("data", data);

        return "/surf";
    }
    @GetMapping("/jeju")
    public String jeju(Model model) {
        String json = restTemplate.getForObject(URL + JEJU, String.class);
        Info info = gson.fromJson(json, Info.class);
        List<SurfInfo> data = info.getResult().getData();
        model.addAttribute("data", data);

        return "/surf";
    }
    @GetMapping("/taean")
    public String taean(Model model) {
        String json = restTemplate.getForObject(URL + TAEAN, String.class);
        Info info = gson.fromJson(json, Info.class);
        List<SurfInfo> data = info.getResult().getData();
        model.addAttribute("data", data);

        return "/surf";
    }
}
