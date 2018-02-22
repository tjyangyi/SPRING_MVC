package com.fhzz.core.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author: YangYi
 * @CreateTime: 2018年2月13日 下午12:22:10
 * @Copyright: FHZZ
 */
public class PageResult<T> implements Serializable {
	private static final long serialVersionUID = -1750386840274995765L;
	
	private long total; // 总记录数
	private List<T> rows; // 查询出的结果数

	public PageResult() {
		super();
	}

	public PageResult(long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageResult [total=" + total + ", rows=" + rows + "]";
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
