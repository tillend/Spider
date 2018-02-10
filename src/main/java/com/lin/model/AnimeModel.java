package com.lin.model;

/**
 * bilibili已完结动漫合集Model
 * 
 * @author god
 *
 */
public class AnimeModel {
	// 徽章?
	private boolean badgepay;
	// 播放数
	private Long play;
	// 描述
	private String description;
	// 合集发布日期
	private String pubdate; // eg.2018-01-31 18:23:54
	// 标题
	private String title;
	// 评论数
	private Long review;
	// 封面图片链接
	private String pic;
	// ?
	private String mid;
	// 视频唯一id
	private String id;
	// url
	private String arcurl;
	// 标签
	private String tag;
	// 历史弹幕总数
	private Long video_review; // eg. 62181
	// 作者
	private String author;
	// 收藏数
	private Long favorites;
	// 期间?
	private Long duration;
	// 类型,eg. video
	private String type;
	//
	private String arcrank;
	//时间戳
	private String senddate; // eg.1517394622

	public boolean isBadgepay() {
		return badgepay;
	}

	public void setBadgepay(boolean badgepay) {
		this.badgepay = badgepay;
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
		this.description = description;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		this.pic = pic;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArcurl() {
		return arcurl;
	}

	public void setArcurl(String arcurl) {
		this.arcurl = arcurl;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getVideo_review() {
		return video_review;
	}

	public void setVideo_review(Long video_review) {
		this.video_review = video_review;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
		this.type = type;
	}

	public String getArcrank() {
		return arcrank;
	}

	public void setArcrank(String arcrank) {
		this.arcrank = arcrank;
	}

	public String getSenddate() {
		return senddate;
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

}
