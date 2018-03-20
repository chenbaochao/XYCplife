package com.sjzxywlkj.cplife.bean;

public class ProfitShow {

	private String month;
	private String lastMonth;
	private String quarterly;
	private String lastQuarterly;
	
	@Override
	public String toString() {
		return "ProfitShow [month=" + month + ", lastMonth=" + lastMonth + ", quarterly=" + quarterly
				+ ", lastQuarterly=" + lastQuarterly + "]";
	}
	public ProfitShow() {
		super();
	}
	public ProfitShow(String month, String lastMonth, String quarterly, String lastQuarterly) {
		super();
		this.month = month;
		this.lastMonth = lastMonth;
		this.quarterly = quarterly;
		this.lastQuarterly = lastQuarterly;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}
	public String getQuarterly() {
		return quarterly;
	}
	public void setQuarterly(String quarterly) {
		this.quarterly = quarterly;
	}
	public String getLastQuarterly() {
		return lastQuarterly;
	}
	public void setLastQuarterly(String lastQuarterly) {
		this.lastQuarterly = lastQuarterly;
	}
	
}
