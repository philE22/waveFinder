package com.surfwave.waveFinder.service;

import com.surfwave.waveFinder.domain.api.JPWaveChart;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class JapanWebCrawler {
    private final String JP_WAVE_URL = "https://www.imocwx.com/cwm.php";
    private final String IMAGE_PATH_URL_FORMAT = "https://www.imocwx.com/cwm/cwm%s_%s%s";

    public List<JPWaveChart> getJpWaveChart(String type) throws IOException {
        Document document = Jsoup.connect(JP_WAVE_URL).get();
        Element main = document.getElementById("main");
        String date = main.getElementsByClass("title").text();

        //src = "cwm/cwmjp_21.png?1212"
        String src = main.getElementsByTag("img").get(0).attr("src");
        String postFix = src.substring(src.indexOf(".png"));

        ArrayList<JPWaveChart> list = new ArrayList<>();

        LocalDateTime startDate = parsingLocalDateTime(date);
        for (int i = 0; i < 25; i++) {
            String imagePath = String.format(IMAGE_PATH_URL_FORMAT, type, String.format("%02d", i), postFix);

            list.add(JPWaveChart.builder()
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

    private LocalDateTime parsingLocalDateTime(String date) {
        //data = "日本 沿岸波浪予想（気象庁提供） 2023年5月12日(金)15時(JST) 更新"
        int yearIndex = date.indexOf("年");
        int monthIndex = date.indexOf("月");
        int dayIndex = date.lastIndexOf("日");
        int hourIndex = date.indexOf("時");

        Integer year = Integer.valueOf(date.substring(yearIndex - 4, yearIndex));
        Integer month = Integer.valueOf(date.substring(yearIndex + 1, monthIndex));
        Integer day = Integer.valueOf(date.substring(monthIndex + 1, dayIndex));
        Integer hour = Integer.valueOf(date.substring(dayIndex + 4, hourIndex));

        return LocalDateTime.of(year, month, day, hour, 0);
    }
}
