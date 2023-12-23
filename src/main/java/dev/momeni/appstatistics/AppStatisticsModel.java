package dev.momeni.appstatistics;

public class AppStatisticsModel {

    public int weekNum;

    public int year;

    public int requests;

    public int clicks;

    public int installs;

    public AppStatisticsModel(int weekNum, int year, int requests, int clicks, int installs) {
        this.weekNum = weekNum;
        this.year = year;
        this.requests = requests;
        this.clicks = clicks;
        this.installs = installs;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRequests() {
        return requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int getInstalls() {
        return installs;
    }

    public void setInstalls(int installs) {
        this.installs = installs;
    }
}
