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
	private int page = 1; // 当前页码
	private int rows = 10; // 每页的记录数
	private int startIndex;// SQL查询起始INDEX
	private int endIndex;// SQL查询结束INDEX

	private void initIndex() {
		this.startIndex = (page - 1) * rows;
		this.endIndex = startIndex + rows;
	}

	public PageParam() {
		super();
		this.initIndex();
	}

	public PageParam(int page, int rows) {
		super();
		this.page = page;
		this.rows = rows;
		this.initIndex();
	}

	public void setPage(int page) {
		this.page = page;
		this.initIndex();
	}

	public void setRows(int rows) {
		this.rows = rows;
		this.initIndex();
	}

	@Override
	public String toString() {
		return "PageParam [page=" + page + ", rows=" + rows + "]";
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}
	public int getPage() {
		return page;
	}
	
	public int getRows() {
		return rows;
	}
}
