package com.lin.model;

import java.util.Date;

public class Bangumi {
    private Integer id;

    private Long favorites;

    private Long play;

    private Long coins;

    private Long videoReview;

    private String title;

    private String url;

    private String updateTime;

    private String evaluate;

    private String seasonId;

    private Date pubTime;

    private Integer totalCount;

    private String pubString;

    private String updatePattern;

    private String isFinish;

    private String newestEpIndex;

    private String cover;

    private String seasonStatus;

    private String week;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getFavorites() {
        return favorites;
    }

    public void setFavorites(Long favorites) {
        this.favorites = favorites;
    }

    public Long getPlay() {
        return play;
    }

    public void setPlay(Long play) {
        this.play = play;
    }

    public Long getCoins() {
        return coins;
    }

    public void setCoins(Long coins) {
        this.coins = coins;
    }

    public Long getVideoReview() {
        return videoReview;
    }

    public void setVideoReview(Long videoReview) {
        this.videoReview = videoReview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate == null ? null : evaluate.trim();
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId == null ? null : seasonId.trim();
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getPubString() {
        return pubString;
    }

    public void setPubString(String pubString) {
        this.pubString = pubString == null ? null : pubString.trim();
    }

    public String getUpdatePattern() {
        return updatePattern;
    }

    public void setUpdatePattern(String updatePattern) {
        this.updatePattern = updatePattern == null ? null : updatePattern.trim();
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish == null ? null : isFinish.trim();
    }

    public String getNewestEpIndex() {
        return newestEpIndex;
    }

    public void setNewestEpIndex(String newestEpIndex) {
        this.newestEpIndex = newestEpIndex == null ? null : newestEpIndex.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getSeasonStatus() {
        return seasonStatus;
    }

    public void setSeasonStatus(String seasonStatus) {
        this.seasonStatus = seasonStatus == null ? null : seasonStatus.trim();
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }
}