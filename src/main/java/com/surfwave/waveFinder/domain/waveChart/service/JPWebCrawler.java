package com.surfwave.waveFinder.domain.waveChart.service;

import com.surfwave.waveFinder.domain.waveChart.entity.JPChart;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JPWebCrawler {
    private final String JP_WAVE_URL = "https://www.imocwx.com/cwm.php";
    private final String IMAGE_PATH_URL_FORMAT = "https://www.imocwx.com/cwm/cwm%s_%s%s";

    //TODO 예외처리 하기
    public List<JPChart> getJpWaveChart(String type) {
        Document document = null;
        try {
            document = Jsoup.connect(JP_WAVE_URL).get();
        } catch (IOException e) {
            throw new RuntimeException("JP 크롤링이 되지 않았습니다.");
        }
        Element main = document.getElementById("main");
        String dateString = main.getElementsByClass("title").text();

        //src = "https://www.imocwx.com/cwm/cwmjp_00.png?2206"
        String absImgPath = main.getElementsByTag("img").get(0).attr("abs:src");

        ArrayList<JPChart> list = new ArrayList<>();

        LocalDateTime startDate = parsingLocalDateTime(dateString);
        for (int i = 0; i < 25; i++) {
            String imagePath = absImgPath.replace("00", String.format("%02d", i));
            list.add(JPChart.builder()
                    .imagePath(imagePath)
                    .fullDateTime(startDate.format(DateTimeFormatter.ofPattern("yyyyMMddHH")))
                    .year(startDate.getYear())
                    .month(startDate.getMonthValue())
                    .day(startDate.getDayOfMonth())
                    .hour(startDate.getHour())
                    .dayOfWeek(startDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA))
                    .build()
            );

            startDate = startDate.plusHours(3L);
        }

        return list;
    }

    private LocalDateTime parsingLocalDateTime(String dateString) {
        //dateString = "日本 沿岸波浪予想（気象庁提供） 2023年5月12日(金)15時(JST) 更新"
        // 년도 추출
        Pattern yearPattern = Pattern.compile("\\d{4}年");
        Matcher yearMatcher = yearPattern.matcher(dateString);
        // 월 추출
        Pattern monthPattern = Pattern.compile("\\d{1,2}月");
        Matcher monthMatcher = monthPattern.matcher(dateString);
        // 일 추출
        Pattern dayPattern = Pattern.compile("\\d{1,2}日");
        Matcher dayMatcher = dayPattern.matcher(dateString);
        // 시간 추출
        Pattern hourPattern = Pattern.compile("\\d{1,2}時");
        Matcher hourMatcher = hourPattern.matcher(dateString);

        //하나라도 파싱이 안되면 에러
        if (!(yearMatcher.find() && monthMatcher.find() && dayMatcher.find() && hourMatcher.find())) {
            throw new RuntimeException("날짜 데이터가 파싱되지 않았습니다.");
        }
        int year = Integer.parseInt(yearMatcher.group().replace("年", ""));
        int month = Integer.parseInt(monthMatcher.group().replace("月", ""));
        int day = Integer.parseInt(dayMatcher.group().replace("日", ""));
        int hour = Integer.parseInt(hourMatcher.group().replace("時", ""));

        return LocalDateTime.of(year, month, day, hour, 0);
    }
}