package com.surfwave.waveFinder.domain.waveChart.service;

import com.surfwave.waveFinder.domain.waveChart.dto.KRChartDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KROpenAPIManager {
    private final RestTemplate restTemplate;
    private final String KR_API_URL_FORMAT = "http://www.khoa.go.kr/api/oceangrid/preOcean/search.do?ResultType=json&type=%s%s";
    @Value("${serviceKey}")
    private String serviceKey;

    public List<KRChartDto> getKRWaveChartList(String type) {
        String json = restTemplate.getForObject(String.format(KR_API_URL_FORMAT, type, serviceKey), String.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject parsing = null;
        try {
            parsing = (JSONObject) jsonParser.parse(json);
        } catch (ParseException e) {
            throw new RuntimeException("json 파싱에 실패했습니다.");
        }

        JSONObject result = (JSONObject) parsing.get("result");
        JSONArray jsonArray = (JSONArray) result.get("data");

        ArrayList<KRChartDto> KRWaveChartList = new ArrayList<>();

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            KRWaveChartList.add(parsingKRWaveChart(jsonObject));
        }

        return KRWaveChartList;
    }

    private KRChartDto parsingKRWaveChart(JSONObject jsonObject) {
        String region = (String) jsonObject.get("name");
        String imagePath = (String) jsonObject.get("filePath");

        LocalDateTime localDateTime = parsingLocalDateTime(jsonObject);

        return new KRChartDto(region, imagePath, localDateTime);
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
