package dev.momeni.appstatistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AppStatisticsServiceTests {

    @Mock
    private AppStatisticsRepository appStatisticsRepository;

    @InjectMocks
    private AppStatisticsService appStatisticsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStatsWhenStatisticsFound() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = formatter.parse("2022-12-5");
        Date endDate = formatter.parse("2023-12-20");
        int type = 1;

        List<AppStatistics> mockStatisticsList = new ArrayList<>();

        mockStatisticsList.add(new AppStatistics(
                "test-app",
                formatter.parse("2023-12-10"),
                1,
                7,
                5,
                2,
                5,
                3,
                2
        ));

        mockStatisticsList.add(new AppStatistics(
                "test-app-2",
                formatter.parse("2023-12-14"),
                1,
                11,
                4,
                4,
                5,
                3,
                4
        ));

        mockStatisticsList.add(new AppStatistics(
                "test-app-3",
                formatter.parse("2023-10-15"),
                1,
                2,
                3,
                6,
                5,
                4,
                6
        ));

        mockStatisticsList.add(new AppStatistics(
                "test-app-3",
                formatter.parse("2022-12-14"),
                1,
                7,
                5,
                3,
                7,
                3,
                2
        ));

        when(appStatisticsRepository.findAppStatisticsByTypeAndDate(startDate, endDate, type))
                .thenReturn(mockStatisticsList);

        ResponseEntity<AppStatisticsListResponse> responseEntity = appStatisticsService
                .getStats(startDate, endDate, type);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetStatsWhenNoStatisticsFound() {
        Date startDate = new Date();
        Date endDate = new Date();
        int type = 1;

        when(appStatisticsRepository.findAppStatisticsByTypeAndDate(startDate, endDate, type))
                .thenReturn(new ArrayList<>());

        ResponseEntity<AppStatisticsListResponse> responseEntity = appStatisticsService
                .getStats(startDate, endDate, type);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testGetStatusWhenExceptionThrown() {
        Date startDate = new Date();
        Date endDate = new Date();
        int type = 1;

        when(appStatisticsRepository.findAppStatisticsByTypeAndDate(startDate, endDate, type))
                .thenThrow(new RuntimeException());

        ResponseEntity<AppStatisticsListResponse> responseEntity = appStatisticsService
                .getStats(startDate, endDate, type);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
