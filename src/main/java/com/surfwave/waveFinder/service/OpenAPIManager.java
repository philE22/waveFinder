package com.surfwave.waveFinder.service;

import com.surfwave.waveFinder.domain.api.KRWaveChart;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class OpenAPIManager {
    private final RestTemplate restTemplate;
    private final String KR_API_URL_FORMAT = "http://www.khoa.go.kr/api/oceangrid/preOcean/search.do?ResultType=json&type=%s%s";
    @Value("${serviceKey}")
    private String serviceKey;

    public List<KRWaveChart> getKRWaveChartList(String type) throws ParseException {
        JSONParser jsonParser = new JSONParser();

        String json = restTemplate.getForObject(String.format(KR_API_URL_FORMAT, type, serviceKey), String.class);

        JSONObject parsing = (JSONObject) jsonParser.parse(json);
        JSONObject result = (JSONObject) parsing.get("result");
        JSONArray jsonArray = (JSONArray) result.get("data");

        ArrayList<KRWaveChart> KRWaveChartList = new ArrayList<>();

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            KRWaveChartList.add(parsingKRWaveChart(jsonObject));
        }

        return KRWaveChartList;
    }

    private KRWaveChart parsingKRWaveChart(JSONObject jsonObject) {
        String name = (String) jsonObject.get("name");
        String imagePath = (String) jsonObject.get("filePath");

        LocalDateTime localDateTime = parsingLocalDateTime(jsonObject);

        return KRWaveChart.builder()
                .name(name)
                .year(localDateTime.getYear())
                .month(localDateTime.getMonthValue())
                .day(localDateTime.getDayOfMonth())
                .hour(localDateTime.getHour())
                .dayOfWeek(localDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA))
                .imagePath(imagePath)
                .build();
    }

    private LocalDateTime parsingLocalDateTime(JSONObject jsonObject) {
        int hour = Integer.parseInt((String) jsonObject.get("hour"));
        //day = "20230512" 형식
        String dateString = (String) jsonObject.get("day");

        int year = Integer.parseInt(dateString.substring(0, 4));
        int month = Integer.parseInt(dateString.substring(4, 6));
        int day = Integer.parseInt(dateString.substring(6));

        return LocalDateTime.of(year, month, day, hour, 0, 0);
    }
}
