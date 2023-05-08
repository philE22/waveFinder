package com.surfwave.waveFinder.service;

import com.surfwave.waveFinder.domain.api.SeaForecastInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpenAPIManager {
    RestTemplate restTemplate = new RestTemplate();

    private final String URL = "http://www.khoa.go.kr/api/oceangrid/preOcean/search.do?ResultType=json";
    @Value("${serviceKey}")
    private String serviceKey;

    public List<SeaForecastInfo> getData(String type) throws ParseException {
        JSONParser jsonParser = new JSONParser();

        String json = restTemplate.getForObject(URL + "&type=" + type + serviceKey, String.class);

        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
        JSONObject result = (JSONObject) jsonObject.get("result");
        JSONArray list = (JSONArray) result.get("data");

        ArrayList<SeaForecastInfo> seaForecastInfoList = new ArrayList<>();

        for (Object o : list) {
            JSONObject data = (JSONObject) o;
            String day = (String) data.get("day");

            SeaForecastInfo forecastInfo = SeaForecastInfo.builder()
                    .name((String) data.get("name"))
                    .year(Integer.parseInt(day.substring(0, 4)))
                    .month(Integer.parseInt(day.substring(4, 6)))
                    .day(Integer.parseInt(day.substring(6)))
                    .hour(Integer.parseInt((String) data.get("hour")))
                    .filePath((String) data.get("filePath"))
                    .build();
            seaForecastInfoList.add(forecastInfo);
        }

        return seaForecastInfoList;
    }
}
