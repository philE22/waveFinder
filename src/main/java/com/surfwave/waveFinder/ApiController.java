package com.surfwave.waveFinder;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApiController {

    RestTemplate restTemplate = new RestTemplate();
    Gson gson = new Gson();
    private final String URL = "http://www.khoa.go.kr/api/oceangrid/preOcean/search.do?ServiceKey=OUkrebOp9eqxGzHu5vA3pw==&type=SOKCHO&ResultType=json";


    @GetMapping("/surf")
    public String surf(Model model) {
        String json = restTemplate.getForObject(URL, String.class);
        System.out.println(json);
        Info info = gson.fromJson(json, Info.class);
        List<SurfInfo> data = info.getResult().getData();
        System.out.println(data);
        model.addAttribute("data", data);

        return "/surf";
    }
}
