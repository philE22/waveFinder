package com.surfwave.waveFinder.domain.waveChart.service;

import com.surfwave.waveFinder.domain.waveChart.dto.KRChartDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class KROpenAPIManagerTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private KROpenAPIManager apiManager;

    @DisplayName("받아온 json을 파싱하여 List<KRChartDto>로 만든다")
    @Test
    void getKRWaveChartListTest() {
        //given
        String json = "{\n" +
                "    \"result\": {\n" +
                "        \"data\": [\n" +
                "            {\n" +
                "                \"name\": \"속초\",\n" +
                "                \"type\": \"SOKCHO\",\n" +
                "                \"fileName\": \"do_sokcho_20230524_09.png\",\n" +
                "                \"hour\": \"09\",\n" +
                "                \"day\": \"20230524\",\n" +
                "                \"filePath\": \"https://www.khoa.go.kr/daily_ocean/Downloads/20230524/do_sokcho_20230524_09.png\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"속초\",\n" +
                "                \"type\": \"SOKCHO\",\n" +
                "                \"fileName\": \"do_sokcho_20230524_12.png\",\n" +
                "                \"hour\": \"12\",\n" +
                "                \"day\": \"20230524\",\n" +
                "                \"filePath\": \"https://www.khoa.go.kr/daily_ocean/Downloads/20230524/do_sokcho_20230524_12.png\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"속초\",\n" +
                "                \"type\": \"SOKCHO\",\n" +
                "                \"fileName\": \"do_sokcho_20230524_15.png\",\n" +
                "                \"hour\": \"15\",\n" +
                "                \"day\": \"20230524\",\n" +
                "                \"filePath\": \"https://www.khoa.go.kr/daily_ocean/Downloads/20230524/do_sokcho_20230524_15.png\"\n" +
                "            },\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        given(restTemplate.getForObject(anyString(), eq(String.class)))
                .willReturn(json);

        //when
        List<KRChartDto> list = apiManager.getKRWaveChartList("test");

        //then
        assertThat(list.size()).isEqualTo(3);
        KRChartDto krChartDto = list.get(0);
        assertThat(krChartDto.getYear()).isEqualTo(2023);
        assertThat(krChartDto.getMonth()).isEqualTo(5);
        assertThat(krChartDto.getDay()).isEqualTo(24);
    }

}