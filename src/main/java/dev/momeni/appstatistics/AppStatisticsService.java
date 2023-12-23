package dev.momeni.appstatistics;

import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.TemporalType;
import net.time4j.calendar.PersianCalendar;
import net.time4j.tz.olson.ASIA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AppStatisticsService {

    @Autowired
    AppStatisticsRepository appStatisticsRepository;

    public ResponseEntity<AppStatisticsListResponse> getStats(Date startDate, Date endDate, int type) {
        try {
            List<AppStatistics> appStatisticsList = new ArrayList<>(
                    appStatisticsRepository.findAppStatisticsByTypeAndDate(startDate, endDate,
                            type));

            if (appStatisticsList.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            List<AppStatisticsModel> groupedStats = groupByWeekAndYear(appStatisticsList);

            return new ResponseEntity<>(new AppStatisticsListResponse(groupedStats), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<AppStatisticsModel> groupByWeekAndYear(List<AppStatistics> appStatisticsList) {
        // Group the given list of app statistics by their year and week number
        Map<String, List<AppStatistics>> groupedStats = appStatisticsList.stream()
                .collect(Collectors.groupingBy(stat -> {
                    // Convert the date to Jalali
                    Moment moment = TemporalType.MILLIS_SINCE_UNIX.translate(
                            stat.getReportTime().getTime());
                    PlainDate geoDate = moment.toZonalTimestamp(ASIA.TEHRAN).toDate();
                    PersianCalendar jalaliDate = geoDate.transform(PersianCalendar.axis());

                    // Concatenate and use the week number and year as key to
                    // calculate the sum of the statistics easily
                    int weekNum = jalaliDate.getInt(PersianCalendar.WEEK_OF_YEAR);
                    int year = jalaliDate.getYear();
                    return weekNum + "-" + year;
                }));

        // Find the entries with same year and week number
        // and calculate sum of the statistics
        return groupedStats.entrySet().stream()
                .map(entry -> {
                    String[] keyParts = entry.getKey().split("-");
                    int weekNum = Integer.parseInt(keyParts[0]);
                    int year = Integer.parseInt(keyParts[1]);

                    List<AppStatistics> value = entry.getValue();

                    int requests = value.stream().mapToInt(stat -> stat.getVideoRequests() + stat.getWebViewRequests()).sum();
                    int clicks = value.stream().mapToInt(stat -> stat.getVideoClicks() + stat.getWebviewClicks()).sum();
                    int installs = value.stream().mapToInt(stat -> stat.getVideoInstalls() + stat.getWebviewInstalls()).sum();

                    return new AppStatisticsModel(weekNum, year, requests, clicks, installs);
                })
                .collect(Collectors.toList());
    }
}
