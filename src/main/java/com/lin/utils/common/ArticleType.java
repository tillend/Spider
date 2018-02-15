package com.lin.utils.common;
/**
 * csdn文章类型
 * @author god
 *
 */
public enum ArticleType {
	
	ORIGINAL(0, "原创"),
	REPOST(1, "转载"),
	TRANSLATED(2, "翻译"),
	UNKNOWN(3, "未知");
	
	//类型对应值
	private int value;
	//文章类型
	private String type;
	
	private ArticleType(int value, String type) {
		this.value = value;
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
