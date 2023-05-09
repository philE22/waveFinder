package com.surfwave.waveFinder.service;

import com.surfwave.waveFinder.domain.api.SeaForecastInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

            String date = (String) data.get("day");
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(4, 6));
            int day = Integer.parseInt(date.substring(6));
            String dayOfWeek = getDayOfWeek(year, month, day);

            SeaForecastInfo forecastInfo = SeaForecastInfo.builder()
                    .name((String) data.get("name"))
                    .year(year)
                    .month(month)
                    .day(day)
                    .dayOfWeek(dayOfWeek)
                    .hour(Integer.parseInt((String) data.get("hour")))
                    .filePath((String) data.get("filePath"))
                    .build();
            seaForecastInfoList.add(forecastInfo);
        }

        return seaForecastInfoList;
    }

    private static String getDayOfWeek(int year, int month, int day) {
        LocalDate localDate = LocalDate.of(year, month, day);
        String dayOfWeek = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA);
        return dayOfWeek;
    }
}
