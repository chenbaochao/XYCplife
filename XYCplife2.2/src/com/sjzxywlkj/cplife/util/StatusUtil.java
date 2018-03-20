package com.sjzxywlkj.cplife.util;

public class StatusUtil {

	public static String tran(String status) {
		switch (status) {
		case "OFFLINE_PAYMENT":
			return "线下支付";
		case "FINISH_PAYMENT":
			return "线上支付";
		case "WAIT_PAYMENT":
			return "待缴费";
		case "OUT_OF_DATE":
			return "未缴费且过期";
		default:
			break;
		}
		return null;
	}
}
