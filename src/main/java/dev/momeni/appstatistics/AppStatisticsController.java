package dev.momeni.appstatistics;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/app-statistics")
public class AppStatisticsController {

    private final AppStatisticsService appStatisticsService;

    public AppStatisticsController(AppStatisticsService appStatisticsService) {
        this.appStatisticsService = appStatisticsService;
    }

    @GetMapping("/stats")
    public ResponseEntity<AppStatisticsListResponse> getStats(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam("type") int type
    ) {
        return appStatisticsService.getStats(startDate, endDate, type);
    }
}
