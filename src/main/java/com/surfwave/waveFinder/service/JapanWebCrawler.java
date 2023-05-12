package com.surfwave.waveFinder.service;

import com.surfwave.waveFinder.domain.api.JapanInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class JapanWebCrawler {
    final String URL = "https://www.imocwx.com/cwm.php";

    public List<JapanInfo> crawling() throws IOException {


        Document document = Jsoup.connect(URL).get();
        String date = document.getElementById("main").getElementsByClass("title").text();
        //日本 沿岸波浪予想（気象庁提供） 2023年5月12日(金)15時(JST) 更新 형태
        LocalDateTime startDate = parsingDate(date);

        ArrayList<JapanInfo> list = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            String index = String.valueOf(i);
            if (i < 10) {
                index = "0" + index;
            }
            String imagePath = String.format("https://www.imocwx.com/cwm/cwmjp_%s.png?1206", index);
            list.add(JapanInfo.builder()
                    .imagePath(imagePath)
                    .day(startDate.getDayOfMonth())
                    .hour(startDate.getHour())
                    .dayOfWeek(startDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA))
                    .build()
            );
            startDate = startDate.plusHours(3L);
        }

        return list;
    }

    private LocalDateTime parsingDate(String date) {
        int yearIndex = date.indexOf("年");
        int monthIndex = date.indexOf("月");
        int dayIndex = date.lastIndexOf("日");
        int hourIndex = date.indexOf("時");

        Integer year = Integer.valueOf(date.substring(yearIndex-4, yearIndex));
        Integer month = Integer.valueOf(date.substring(yearIndex + 1, monthIndex));
        Integer day = Integer.valueOf(date.substring(monthIndex + 1, dayIndex));
        Integer hour = Integer.valueOf(date.substring(dayIndex+4, hourIndex));

        return LocalDateTime.of(year, Month.of(month), day, hour, 0);
    }
}
