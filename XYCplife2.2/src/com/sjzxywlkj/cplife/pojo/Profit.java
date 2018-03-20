package com.sjzxywlkj.cplife.pojo;

import java.math.BigDecimal;
import java.util.Calendar;

public class Profit {

	private String id;
	private BigDecimal jan;
	private BigDecimal feb;
	private BigDecimal mar;
	private BigDecimal apr;
	private BigDecimal may;
	private BigDecimal jun;
	private BigDecimal jul;
	private BigDecimal aug;
	private BigDecimal sep;
	private BigDecimal oct;
	private BigDecimal nov;
	private BigDecimal dece;
	
	
	@Override
	public String toString() {
		return "Profit [id=" + id + ", jan=" + jan + ", feb=" + feb + ", mar=" + mar + ", apr=" + apr + ", may=" + may
				+ ", jun=" + jun + ", jul=" + jul + ", aug=" + aug + ", sep=" + sep + ", oct=" + oct + ", nov=" + nov
				+ ", dece=" + dece + "]";
	}

	public Profit() {
		super();
	}

	public Profit(String id, BigDecimal jan, BigDecimal feb, BigDecimal mar, BigDecimal apr, BigDecimal may,
			BigDecimal jun, BigDecimal jul, BigDecimal aug, BigDecimal sep, BigDecimal oct, BigDecimal nov,
			BigDecimal dece) {
		super();
		this.id = id;
		this.jan = jan;
		this.feb = feb;
		this.mar = mar;
		this.apr = apr;
		this.may = may;
		this.jun = jun;
		this.jul = jul;
		this.aug = aug;
		this.sep = sep;
		this.oct = oct;
		this.nov = nov;
		this.dece = dece;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getJan() {
		return jan;
	}

	public void setJan(BigDecimal jan) {
		this.jan = jan;
	}

	public BigDecimal getFeb() {
		return feb;
	}

	public void setFeb(BigDecimal feb) {
		this.feb = feb;
	}

	public BigDecimal getMar() {
		return mar;
	}

	public void setMar(BigDecimal mar) {
		this.mar = mar;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public BigDecimal getMay() {
		return may;
	}

	public void setMay(BigDecimal may) {
		this.may = may;
	}

	public BigDecimal getJun() {
		return jun;
	}

	public void setJun(BigDecimal jun) {
		this.jun = jun;
	}

	public BigDecimal getJul() {
		return jul;
	}

	public void setJul(BigDecimal jul) {
		this.jul = jul;
	}

	public BigDecimal getAug() {
		return aug;
	}

	public void setAug(BigDecimal aug) {
		this.aug = aug;
	}

	public BigDecimal getSep() {
		return sep;
	}

	public void setSep(BigDecimal sep) {
		this.sep = sep;
	}

	public BigDecimal getOct() {
		return oct;
	}

	public void setOct(BigDecimal oct) {
		this.oct = oct;
	}

	public BigDecimal getNov() {
		return nov;
	}

	public void setNov(BigDecimal nov) {
		this.nov = nov;
	}

	public BigDecimal getDece() {
		return dece;
	}

	public void setDece(BigDecimal dece) {
		this.dece = dece;
	}

	public BigDecimal quarterly(){
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		if (month<3) {
			return jan.add(feb).add(mar);
			
		}else {
			if (month<6) {
				return apr.add(may).add(jun);
			} else {
				if (month<9) {
					return jul.add(aug).add(sep);
				} else {
					return oct.add(nov).add(dece);
				}
			}
		}
		
	}
	
	public BigDecimal lastQuarterly(){
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		if (month<3) {
			
			return oct.add(nov).add(dece);
			
		}else {
			if (month<6) {
				
				return jan.add(feb).add(mar);
				
			
			} else {
				if (month<9) {
					
					return apr.add(may).add(jun);
					
					
				} else {
					
					return jul.add(aug).add(sep);
					
				}
			}
		}
		
	}
	
	public BigDecimal lastMonth(){
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		
		switch (month) {
		case 1:return this.jan;
		case 2:return this.feb;
		case 3:return this.mar;
		case 4:return this.apr;
		case 5:return this.may;
		case 6:return this.jun;
		case 7:return this.jul;	
		case 8:return this.aug;
		case 9:return this.sep;
		case 10:return this.oct;
		case 11:return this.nov;
		case 0:return this.dece;
		default:
			break;
		}
		return null;
		
	}
	public BigDecimal month(){
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		
		switch (month) {
		case 0:return this.jan;
		case 1:return this.feb;
		case 2:return this.mar;
		case 3:return this.apr;
		case 4:return this.may;
		case 5:return this.jun;
		case 6:return this.jul;	
		case 7:return this.aug;
		case 8:return this.sep;
		case 9:return this.oct;
		case 10:return this.nov;
		case 11:return this.dece;
		default:
			break;
		}
		return null;
		
	}
	

}
