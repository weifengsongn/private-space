package com.swf.common.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: private-space
 * @description:
 * @author: Wfsong
 * @create: 2020-04-21 21:30
 **/
public class PageResult<T> {

	@JsonIgnore
	private static PageResult EMPTY_RESULT = create(0, new ArrayList());

	private long total = 0;
	private List<? extends T> rows;

	private PageResult(){

	}

// 为什么不用构造函数呢？
	public static<T> PageResult<T> create(long total, List<T> rows) {
		PageResult<T> result = new PageResult<>();
		result.setRows(rows == null? new ArrayList<T>() : rows);
		result.setTotal(total);
		return result;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<? extends T> getRows() {
		return rows;
	}

	public void setRows(List<? extends T> rows) {
		this.rows = rows;
	}
}
