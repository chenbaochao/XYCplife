package com.sjzxywlkj.cplife.util;

import java.util.List;
import java.util.Random;

public class Page<T>{
	private Integer currentpage;
	private Integer pagesize=3;
	private Integer total;
	private Integer pagecount;
	private Integer pre;
	private Integer next;
	private int start;
	private List<T> rows;
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPagecount() {
		return (total+pagesize-1)/pagesize;
	}
	
	public Integer getPre() {
		if(currentpage>1)
			return currentpage-1;
		return 1;
	}
	
	public Integer getNext() {
		if(currentpage<getPagecount())
			return currentpage+1;
		return getPagecount();
	}
	
	public int getStart() {
		return (currentpage-1)*pagesize;
	}
	
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
