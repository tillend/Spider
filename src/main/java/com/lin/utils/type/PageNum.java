
package com.lin.utils.type;

/**
 * 页数枚举
 * @author god
 *
 */
public enum PageNum {
	
	BANGIMI(151);
	
	private int pageNum;
	
	private PageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	
}
