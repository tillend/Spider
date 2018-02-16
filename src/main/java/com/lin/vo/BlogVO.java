package com.lin.vo;

public class BlogVO {

	private String articleId;
	private String linkTitle;
	private String type;
	private String publishDateTime;
	private String viewNum;
	private String commentNum;
	private String detailUrl;
	private String summary;
	private String blogerId;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPublishDateTime() {
		return publishDateTime;
	}

	public void setPublishDateTime(String publishDateTime) {
		this.publishDateTime = publishDateTime;
	}

	public String getViewNum() {
		return viewNum;
	}

	public void setViewNum(String viewNum) {
		this.viewNum = viewNum;
	}

	public String getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getBlogerId() {
		return blogerId;
	}

	public void setBlogerId(String blogerId) {
		this.blogerId = blogerId;
	}

}
