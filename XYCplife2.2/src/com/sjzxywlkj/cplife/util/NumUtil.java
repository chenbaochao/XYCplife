package com.sjzxywlkj.cplife.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NumUtil {
	
	//批量上传账单流水号
	private static long bill_batch_id = 0;
	//账单流水号
	private static long bill_entry_id = 0;
	//长度
    private static int length = 10;
      
    
    public static synchronized String getBill_batch_id() {
    	bill_batch_id = bill_batch_id >= 9999999999999999L ? 0 : bill_batch_id + 1;  
        String datetime = new SimpleDateFormat("yyyyMMddHHmmss")  
        .format(new Date());
        String serialNum = Long.toString(bill_batch_id);
        return datetime +addLeftZero(serialNum, length);
    }  
    
    public static synchronized String getBill_entry_id() {
    	bill_entry_id = bill_entry_id >= 9999999999999999L ? 0 : bill_entry_id + 1;  
        String datetime = new SimpleDateFormat("yyyyMMddHHmmss")  
        .format(new Date());
        String serialNum = Long.toString(bill_entry_id);
        return datetime +addLeftZero(serialNum, length);
    }  
    
    private static String addLeftZero(String serialNum, int length) {  
        int old = serialNum.length();  
        if (length > old) {  
            char[] standard = new char[length];
            char[] now = serialNum.toCharArray();
            if (now.length > length) {  
                throw new IllegalArgumentException(  
                        "Numeric value is larger than intended length: " + serialNum  
                                + " LEN " + length);  
            }  
            int lim = standard.length - now.length;  
            for (int i = 0; i < lim; i++) {  
            	standard[i] = '0';  
            }  
            System.arraycopy(now, 0, standard, lim, now.length);  
            return new String(standard);  
        }  
        return serialNum.substring(0, length);  
  
    } 
}

