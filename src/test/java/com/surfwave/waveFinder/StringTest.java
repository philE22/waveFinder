package com.surfwave.waveFinder;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;


public class StringTest {
    private String json = "{\"result\":{\"surfInfo\":[{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230404_09.png\",\"hour\":\"09\",\"day\":\"20230404\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230404_09.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230404_12.png\",\"hour\":\"12\",\"day\":\"20230404\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230404_12.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230404_15.png\",\"hour\":\"15\",\"day\":\"20230404\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230404_15.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230404_18.png\",\"hour\":\"18\",\"day\":\"20230404\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230404_18.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230404_21.png\",\"hour\":\"21\",\"day\":\"20230404\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230404_21.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230405_00.png\",\"hour\":\"00\",\"day\":\"20230405\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230405_00.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230405_03.png\",\"hour\":\"03\",\"day\":\"20230405\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230405_03.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230405_06.png\",\"hour\":\"06\",\"day\":\"20230405\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230405_06.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230405_09.png\",\"hour\":\"09\",\"day\":\"20230405\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230405_09.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230405_12.png\",\"hour\":\"12\",\"day\":\"20230405\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230405_12.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230405_15.png\",\"hour\":\"15\",\"day\":\"20230405\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230405_15.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230405_18.png\",\"hour\":\"18\",\"day\":\"20230405\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230405_18.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230405_21.png\",\"hour\":\"21\",\"day\":\"20230405\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230405_21.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230406_00.png\",\"hour\":\"00\",\"day\":\"20230406\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230406_00.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230406_03.png\",\"hour\":\"03\",\"day\":\"20230406\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230406_03.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230406_06.png\",\"hour\":\"06\",\"day\":\"20230406\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230406_06.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230406_09.png\",\"hour\":\"09\",\"day\":\"20230406\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230406_09.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230406_12.png\",\"hour\":\"12\",\"day\":\"20230406\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230406_12.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230406_15.png\",\"hour\":\"15\",\"day\":\"20230406\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230406_15.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230406_18.png\",\"hour\":\"18\",\"day\":\"20230406\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230406_18.png\"},{\"name\":\"속초\",\"type\":\"SOKCHO\",\"fileName\":\"do_sokcho_20230406_21.png\",\"hour\":\"21\",\"day\":\"20230406\",\"filePath\":\"https://www.khoa.go.kr/daily_ocean/Downloads/20230404/do_sokcho_20230406_21.png\"}]}}\n";
    private Gson gson = new Gson();


    @Test
    void name() {
        Info info = gson.fromJson(json, Info.class);
        System.out.println(info.getResult().getData());
        info.getResult().getData().stream().forEach(
                surfInfo -> {
                    System.out.println(surfInfo);
                }
        );

//        SurfInfo surfInfo = new SurfInfo();
//        surfInfo.setName("aaa");
//        surfInfo.setFileName("bbbb");
//        surfInfo.setDay("123");
//        surfInfo.setType("fwaef");
//        surfInfo.setHour("123123");
//        Result result2 = new Result();
//        result2.setSurfInfo(Arrays.asList(surfInfo));
//        System.out.println(gson.toJson(result2));
    }
}
