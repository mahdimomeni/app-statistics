package dev.momeni.appstatistics;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "app_statistics")
public class AppStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appId;

    private Date reportTime;

    private int type;

    private int videoRequests;

    private int webViewRequests;

    private int  videoClicks;

    private int webviewClicks;

    private int videoInstalls;

    private int webviewInstalls;

    public AppStatistics() {}

    public AppStatistics(
            String appId,
            Date reportTime,
            int type,
            int videoRequests,
            int webViewRequests,
            int videoClicks,
            int webviewClicks,
            int videoInstalls,
            int webviewInstalls
    ) {
        this.appId = appId;
        this.reportTime = reportTime;
        this.type = type;
        this.videoRequests = videoRequests;
        this.webViewRequests = webViewRequests;
        this.videoClicks = videoClicks;
        this.webviewClicks = webviewClicks;
        this.videoInstalls = videoInstalls;
        this.webviewInstalls = webviewInstalls;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setVideoRequests(int videoRequests) {
        this.videoRequests = videoRequests;
    }

    public void setWebViewRequests(int webViewRequests) {
        this.webViewRequests = webViewRequests;
    }

    public void setVideoClicks(int videoClicks) {
        this.videoClicks = videoClicks;
    }

    public void setWebviewClicks(int webviewClicks) {
        this.webviewClicks = webviewClicks;
    }

    public void setVideoInstalls(int videoInstalls) {
        this.videoInstalls = videoInstalls;
    }

    public void setWebviewInstalls(int webviewInstalls) {
        this.webviewInstalls = webviewInstalls;
    }

    public Long getId() {
        return id;
    }

    public String getAppId() {
        return appId;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public int getType() {
        return type;
    }

    public int getVideoRequests() {
        return videoRequests;
    }

    public int getWebViewRequests() {
        return webViewRequests;
    }

    public int getVideoClicks() {
        return videoClicks;
    }

    public int getWebviewClicks() {
        return webviewClicks;
    }

    public int getVideoInstalls() {
        return videoInstalls;
    }

    public int getWebviewInstalls() {
        return webviewInstalls;
    }
}
