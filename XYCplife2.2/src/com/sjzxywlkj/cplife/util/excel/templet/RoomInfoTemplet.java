package com.sjzxywlkj.cplife.util.excel.templet;

import java.util.Collections;
import java.util.LinkedHashSet;

public class RoomInfoTemplet implements ExcelTempt{
	private String[] column_name;//={"房间编号","小区编号","所在分组","楼栋","单元","房号","详细地址"};
	private LinkedHashSet<String>propertyHeader;

	
	private static RoomInfoTemplet templet;
	private RoomInfoTemplet(){}
	
	public static synchronized RoomInfoTemplet getTemplet(){
		if(templet==null){
			templet=new RoomInfoTemplet();
			templet.column_name=new String[7];
			templet.column_name[0]="房间编号";
			templet.column_name[1]="小区编号";
			templet.column_name[2]="所在分组";
			templet.column_name[3]="楼栋";
			templet.column_name[4]="单元";
			templet.column_name[5]="房号";
			templet.column_name[6]="详细地址";
			templet.propertyHeader=new LinkedHashSet<String>();
			Collections.addAll(templet.propertyHeader,"outRoomId","communityId","groups","building","unit","room","address");
		}
		
		return templet;
		
		
	}

	public String[] getColumn_name() {
		return column_name;
	}

	public LinkedHashSet<String> getPropertyHeader() {
		return propertyHeader;
	}

	
	
	
}
