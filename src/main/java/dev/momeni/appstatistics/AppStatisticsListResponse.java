package dev.momeni.appstatistics;

import java.util.List;

public class AppStatisticsListResponse {

    public List<AppStatisticsModel> stats;

    public AppStatisticsListResponse(List<AppStatisticsModel> stats) {
        this.stats = stats;
    }

    public List<AppStatisticsModel> getStats() {
        return stats;
    }

    public void setStats(List<AppStatisticsModel> stats) {
        this.stats = stats;
    }
}