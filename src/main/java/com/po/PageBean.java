package com.po;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable {
	private Integer rows=5;
	private Integer page=5;
	private Integer maxpage;
	private List<?> pagelist;
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageBean(Integer rows, Integer page, Integer maxpage, List<?> pagelist) {
		super();
		this.rows = rows;
		this.page = page;
		this.maxpage = maxpage;
		this.pagelist = pagelist;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getMaxpage() {
		return maxpage;
	}
	public void setMaxpage(Integer maxpage) {
		this.maxpage = maxpage;
	}
	public List<?> getPagelist() {
		return pagelist;
	}
	public void setPagelist(List<?> pagelist) {
		this.pagelist = pagelist;
	}
	@Override
	public String toString() {
		return "PageBean [rows=" + rows + ", page=" + page + ", maxpage=" + maxpage + ", pagelist=" + pagelist + "]";
	}
	
}
