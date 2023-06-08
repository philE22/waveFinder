package com.surfwave.waveFinder.domain.waveChart.service;

import com.surfwave.waveFinder.domain.waveChart.dto.DayDto;
import com.surfwave.waveFinder.domain.waveChart.repository.JPChartImageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JPChartServiceTest {

    @InjectMocks
    private JPChartService service;

    @Mock
    private JPChartImageRepository repository;

    /**
     * 1시 30분에 처음 그날의 차트(00시) 데이터가 들어오기 때문에 1시 31분 부터 그날의 과거차트를 볼 수 있도록
     * 경우의 수
     * 27일 23시 29분 -> 27일 26일
     * 28일 0시 1초 -> 28일 포함되면 안됨 27, 26
     * 28일 1시 29분 -> 동일
     * 28일 1시 31분 -> 28일 0시의 데이터가 있으므로 포함되어야함 28, 27, 26일
     * 즉, 1시 30분이상인지를 확인해서
     */

    @Test
    @DisplayName("1시 30분 59초까지는 그날이 dto에 포함되지않는다")
    void getDayDto31() {
        LocalDateTime today = LocalDateTime.of(2023, 5, 28, 1, 30, 59);
        List<DayDto> dayDto = service.getDayDto(today);

        assertThat(dayDto.size()).isEqualTo(2);
        assertThat(dayDto.get(0).getDay()).isEqualTo(27);
        assertThat(dayDto.get(1).getDay()).isEqualTo(26);
    }

    @Test
    @DisplayName("1시 31분 이후면 그날이 dto에 포함된다")
    void getDayDto2() {
        LocalDateTime today = LocalDateTime.of(2023, 5, 28, 1, 31, 0);
        List<DayDto> dayDto = service.getDayDto(today);

        assertThat(dayDto.size()).isEqualTo(3);
        assertThat(dayDto.get(0).getDay()).isEqualTo(28);
        assertThat(dayDto.get(1).getDay()).isEqualTo(27);
    }
    @Test
    @DisplayName("0시 30분 이면 그날이 dto에 포함되지않는다")
    void getDayDto3() {
        LocalDateTime today = LocalDateTime.of(2023, 5, 28, 0, 30, 0);
        List<DayDto> dayDto = service.getDayDto(today);

        assertThat(dayDto.size()).isEqualTo(2);
        assertThat(dayDto.get(0).getDay()).isEqualTo(27);
        assertThat(dayDto.get(1).getDay()).isEqualTo(26);
    }
}