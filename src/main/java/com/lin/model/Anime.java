package com.lin.model;

import java.util.Date;

import com.lin.utils.DateUtil;
import com.lin.utils.StringUtil;
import com.lin.vo.AnimeVO;

public class Anime {
    private Integer id;

    private String videoId;

    private Long play;

    private String description;

    private String title;

    private Date pubdate;

    private Long review;

    private String pic;

    private String mid;

    private String arcurl;

    private String tag;

    private Long videoReview;

    private String author;

    private Long favorites;

    private Long duration;

    private String type;

    private String arcrank;

    private String senddate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId == null ? null : videoId.trim();
    }

    public Long getPlay() {
        return play;
    }

    public void setPlay(Long play) {
        this.play = play;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public Long getReview() {
        return review;
    }

    public void setReview(Long review) {
        this.review = review;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
    }

    public String getArcurl() {
        return arcurl;
    }

    public void setArcurl(String arcurl) {
        this.arcurl = arcurl == null ? null : arcurl.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Long getVideoReview() {
        return videoReview;
    }

    public void setVideoReview(Long videoReview) {
        this.videoReview = videoReview;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Long getFavorites() {
        return favorites;
    }

    public void setFavorites(Long favorites) {
        this.favorites = favorites;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getArcrank() {
        return arcrank;
    }

    public void setArcrank(String arcrank) {
        this.arcrank = arcrank == null ? null : arcrank.trim();
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate == null ? null : senddate.trim();
    }
    
    public static Anime turnVO2Model(AnimeVO t) {
    	Anime record = new Anime();
		record.setId(null);
		record.setArcrank(t.getArcrank());
		record.setArcurl(t.getArcurl());
		record.setAuthor(t.getAuthor());
		record.setDescription(t.getDescription());
		record.setDuration(t.getDuration());
		record.setFavorites(t.getFavorites());
		record.setMid(t.getMid());
		record.setPic(t.getPic());
		record.setPlay(StringUtil.long2String(t.getPlay()));
		record.setPubdate(DateUtil.string2Date(t.getPubdate()));
		record.setReview(t.getReview());
		record.setSenddate(t.getSenddate());
		record.setTag(t.getTag());
		record.setTitle(t.getTitle());
		record.setType(t.getType());
		record.setVideoId(t.getId());
		record.setVideoReview(t.getVideo_review());
		
		return record;
    }
}