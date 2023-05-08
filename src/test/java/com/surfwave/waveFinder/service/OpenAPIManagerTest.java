package com.surfwave.waveFinder.service;

import com.surfwave.waveFinder.domain.api.SeaForecastInfo;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

class OpenAPIManagerTest {

    @Test
    void name() throws ParseException {
        OpenAPIManager openAPIManager = new OpenAPIManager();
        ReflectionTestUtils.setField(openAPIManager, "serviceKey", "&ServiceKey=OUkrebOp9eqxGzHu5vA3pw==");
        List<SeaForecastInfo> sokcho = openAPIManager.getData("SOKCHO");
        sokcho.stream()
                .forEach(info -> {
                    System.out.println(info.getName());
                    System.out.println(info.getYear());
                    System.out.println(info.getMonth());
                    System.out.println(info.getDay());
                    System.out.println(info.getHour());
                    System.out.println(info.getFilePath());
                    System.out.println("==================");
                });
    }
}