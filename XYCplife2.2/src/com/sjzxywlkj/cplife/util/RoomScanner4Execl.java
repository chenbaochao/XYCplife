package com.sjzxywlkj.cplife.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.sjzxywlkj.cplife.alipay.request.entity.Room;
import com.sjzxywlkj.cplife.util.excel.exception.ExcelDataException;

public class RoomScanner4Execl {
	private static final DecimalFormat decimalF = new DecimalFormat("#.##");  
    private static final DateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");  
	private static final String room_column_index="0245";
	
	private Workbook workbook;
	
	public RoomScanner4Execl(Workbook workbook){
		this.workbook=workbook;
	}
	public LinkedHashSet<String>getRoomId() throws ExcelDataException{
		LinkedHashSet<String>set=new LinkedHashSet<String>();
		try {
            Sheet sheet = null;  
            Row row = null;  
            Cell cell = null;  
            String tmpstr = null;
            
            sheet = workbook.getSheetAt(0);//读取第一个sheet 
            int rowIndex=sheet.getFirstRowNum();
            
            for(Iterator<?> rows = sheet.iterator(); rows.hasNext(); rowIndex++ ){
            	 row = (Row) rows.next();
            	 if(rowIndex <= 1) continue;//从第二行开始读取.因为第一行可能是表头
            	 if(row != null){  
                     int columIndex = 0;  
                     int lastCellNum = 2;
                     while (columIndex < lastCellNum) {  
                         cell = row.getCell(columIndex);  
                         if(cell != null){
                        	 if(columIndex == 0) {//第一列ID为标志列，不解析  
                                 columIndex++;  
                                 continue;  
                             }
	                    	 switch (cell.getCellType()) {  
	                         case Cell.CELL_TYPE_NUMERIC:  
	                             if(HSSFDateUtil.isCellDateFormatted(cell)){  
	                                 Date date = cell.getDateCellValue();  
	                                 tmpstr = dateF.format(date);  
	                             } else {  
	                                 tmpstr = decimalF.format(cell.getNumericCellValue());  
	                             }  
	                             break;  
	                         default:  
	                             tmpstr = cell.getStringCellValue().trim();  
	                             break;  
	                         }
	                    	set.add(tmpstr); 
                         }else{
                        	 String column_index=String.valueOf(columIndex);  
                             switch (column_index) {
								case "0":
									throw new ExcelDataException("错误行数:"+(rowIndex+1)+",错误原因:id有误，请检查数据是否为空。");
								case "1":
									throw new ExcelDataException("错误行数:"+(rowIndex+1)+",错误原因:房间信息解析失败，请检查数据是否为空。");
								default:
									break;
                             }
                         }  
                         columIndex++;
                     }
                 }
            	 else {  
                     System.err.println("该行为空行或者表格中无数据");  
                 }
            }
        }catch(ExcelDataException ex){
        	throw ex;
        }
		catch(Exception e){
	    	 e.printStackTrace();  
	         System.err.println("解析失败!");  
	         return null;  
        }
        return set;	

		
	}
	
	
	public List<Room>getRooms() throws ExcelDataException{
		List<Room>rooms=new ArrayList<Room>();
		
		try {
			List<String[]> list = new ArrayList<String[]>();
            Sheet sheet = null;  
            Row row = null;  
            Cell cell = null;  
            String tmpstr = null;
            
            sheet = workbook.getSheetAt(0);//读取第一个sheet 
            int rowIndex=sheet.getFirstRowNum();
            
            for(Iterator<?> rows = sheet.iterator(); rows.hasNext(); rowIndex++ ){
            	 row = (Row) rows.next();
            	 if(rowIndex <= 1) continue;//从第二行开始读取.因为第一行可能是表头
            	 
            	 if(row != null){  
                     int columIndex = 0;  
                     int lastCellNum = 6;
                     String[] aCells = new String[lastCellNum-1];//首行可能是id
                     
                     while (columIndex < lastCellNum) {  
                         cell = row.getCell(columIndex);  
                         if(cell != null){
                        	 if(columIndex == 0) {//第一列ID为标志列，不解析  
                                 columIndex++;  
                                 continue;  
                             }
	                    	 switch (cell.getCellType()) {  
	                         case Cell.CELL_TYPE_NUMERIC:  
	                             if(HSSFDateUtil.isCellDateFormatted(cell)){  
	                                 Date date = cell.getDateCellValue();  
	                                 tmpstr = dateF.format(date);  
	                             } else {  
	                                 tmpstr = decimalF.format(cell.getNumericCellValue());  
	                             }  
	                             break;  
	                         default:  
	                             tmpstr = cell.getStringCellValue().trim();  
	                             break;  
	                         }
                         }else{
                        	 String column_index=String.valueOf(columIndex);
                        	 if(room_column_index.contains(column_index)){  
                                 switch (column_index) {
								case "0":
									throw new ExcelDataException("错误行数:"+(rowIndex+1)+",错误原因:id有误，请检查数据是否为空。");
								case "2":
									throw new ExcelDataException("错误行数:"+(rowIndex+1)+",错误原因:楼栋信息解析失败，请检查数据是否为空。");
								case "4":
									throw new ExcelDataException("错误行数:"+(rowIndex+1)+",错误原因:房号解析失败，请检查数据是否为空。");
								case "5":
									throw new ExcelDataException("错误行数:"+(rowIndex+1)+",错误原因:详细地址解析失败，请检查数据是否为空。");
								default:
									break;
								}
                             } 
                        	 tmpstr =null;
                         }
                         aCells[columIndex-1] = tmpstr;  
                         columIndex++;
                     }
                     list.add(aCells);
                 }
            	 else {  
                     System.err.println("该行为空行或者表格中无数据");  
                 }
            }
            for(String[]roomdata:list){
            	rooms.add(new Room(null,
            			roomdata[0],roomdata[1],roomdata[2],roomdata[3],roomdata[4]));       
            }
        }catch(ExcelDataException ex){
        	throw ex;
        }
		catch(Exception e){
	    	 e.printStackTrace();  
	         System.err.println("解析失败!");  
	         return null;  
        }
        return rooms;	
    }
}
