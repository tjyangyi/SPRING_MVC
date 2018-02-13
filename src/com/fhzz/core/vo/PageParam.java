/**
 * 
 */
package com.fhzz.core.vo;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月13日 上午11:13:49
 * @Copyright: FHZZ
 */
public class PageParam {
	private int pageIndex = 1; // 当前页码
	private int pageSize = 10; // 每页的记录数
	private int startIndex;// SQL查询起始INDEX
	private int endIndex;// SQL查询结束INDEX

	private void initIndex() {
		this.startIndex = (pageIndex - 1) * pageSize;
		this.endIndex = startIndex + pageSize;
	}

	public PageParam() {
		super();
		this.initIndex();
	}

	public PageParam(int pageIndex, int pageSize) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.initIndex();
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
		this.initIndex();
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.initIndex();
	}

	@Override
	public String toString() {
		return "PageParam [pageIndex=" + pageIndex + ", pageSize=" + pageSize + "]";
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

}
