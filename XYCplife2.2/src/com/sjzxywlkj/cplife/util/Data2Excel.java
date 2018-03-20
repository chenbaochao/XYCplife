package com.sjzxywlkj.cplife.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.sjzxywlkj.cplife.util.excel.templet.ExcelTempt;

public class Data2Excel {
	public <T> HSSFWorkbook generateExcel(List<T>list,ExcelTempt tempt,String title){
		 HSSFWorkbook book = new HSSFWorkbook();
		 try{
			HSSFSheet sheet = book.createSheet("Sheet1");
			sheet.autoSizeColumn(1, true);//自适应列宽度
			
			String[]column_name=tempt.getColumn_name();
			LinkedHashSet<String>propertyHeader =tempt.getPropertyHeader();
	        //填充表头标题  
	        int colSize = column_name.length;  
	        	//合并单元格供标题使用(表名)  
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colSize-1));  
            HSSFRow firstRow = sheet.createRow(0);//第几行（从0开始）  
            HSSFCell firstCell = firstRow.createCell(0);  
            firstCell.setCellValue(title);  
            
            //填充表头header  
            HSSFRow row = sheet.createRow(1);  
            for(int i=0; i< column_name.length; i++) {
	             String key = column_name[i];  
	             HSSFCell cell = row.createCell(i);  
	             cell.setCellValue(key);  
	             
            }
            //获取T类对象属性的get方法
            List<Method>methods=new ArrayList<Method>();
            Class<? extends Object> tCls = list.get(0).getClass();
            for(String property:propertyHeader){
           	 	String getMethodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);  
                Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                methods.add(getMethod);
            }
            //将类的值写入cell
            for(int i=0; i<list.size(); i++) {
            	//i：第几行
                HSSFRow row2 = sheet.createRow(i+2);  
                T data=list.get(i);
                for(int j=0;j<column_name.length;j++){  
                    HSSFCell cell = row2.createCell(j);  
                    try {  
                        //利用反射机制获取dataSet中的属性值，填进cell中  
                        String value = (String)methods.get(j).invoke(data, new Object[] {}); //调用getter从data中获取数据             
                        cell.setCellValue(value);   
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }    
                }  
            }  	 
		 }catch(Exception e){
			 e.printStackTrace();
			 return null;
		 }		 
		 return book;
	}
}
