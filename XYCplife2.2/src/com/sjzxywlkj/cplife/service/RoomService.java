package com.sjzxywlkj.cplife.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.sjzxywlkj.cplife.bean.RoomDto;
import com.sjzxywlkj.cplife.pojo.Roominfo;

public interface RoomService {
	public void test();
	
	/**
	* 添加单个房间
	* @param roomdto 房间信息传输对象
	* @return String 执行结果信息
	*/
	public String addone(RoomDto roomdto);
	//按传入的excel表格批量添加房间
	
	/**
	* 按传入的excel表格批量添加房间
	* @param community_id 物业小区编码
	* 		 	file 	     前端接收的MultipartFile对象	  
	* @return String 执行结果信息
	*/
	public String addbyFile(String community_id,MultipartFile file);
	
	/**
	* 按传入的excel表格批量删除房间
	* @param community_id 物业小区编码
	* 		 	file 	     前端接收的MultipartFile对象	  
	* @return String 执行结果信息
	*/
	public String deletebyFile(String community_id, MultipartFile file);
	//按传入的roomid集合批量删除房间
	/**
	* 按传入的房间编号集合批量删除房间
	* @param  id_list 	     房间id集合
	* 		community_id  物业小区编码	  
	* @return String 执行结果信息
	*/
	public String del(List<String> id_list,String community_id);
	
	/**
	* 按条件精确查询对应房间数量
	* 
	* @param  roomdto 	     包含查询条件的房间信息传输对象
	* 		Merchant_pid  物业公司id编码	  
	* 
	* @return 	int 	     数量
	*/
	public int countRoom(RoomDto roomdto,String Merchant_pid);
	
	/**
	* 按条件精确查询对应房间，支持了后端分页
	* 
	* @param  roomdto 	     包含查询条件的房间信息传输对象
	* 		  start		     数据在查询列表中的起始位置（mysql数据库格式）
	* 		  size		     每页显示的数据条数
	* 		Merchant_pid  物业公司id编码	  
	* 
	* @return List<Roominfo>   满足条件的房间类集合
	*/
	public List<Roominfo>query(RoomDto dto,int start,int size,String Merchant_pid);
	
	/**
	* 按条件精确查询对应房间，支持了后端分页
	* 
	* @param  roomdto 	     包含查询条件的房间信息传输对象
	* 		Merchant_pid  物业公司id编码	  
	* 
	* @return List<Roominfo>   满足条件的房间类集合
	*/
	public List<Roominfo>query(RoomDto dto,String Merchant_pid);
	
	/**
	* 按物业小区查询名下房间id
	* 
	* @param  community_id     物业小区id	  
	* @return List<String>   满足条件的房间类out_room_id集合
	*/
	public List<Roominfo>query(String community_id);
	
	/**
	* 按房间id查询房间详细地址
	* 
	* @param  out_room_id     房间id	  
	* @return 	String   	  房间详细地址
	*/
	public String getAddressByOid(String out_room_id);
	
	/**
	* 按小区id查询小区分组集合
	* 
	* @param  community_id     房间id	  
	* @return List<String>     小区分组集合
	*/
	public List<String> getGroupsByCid(String community_id);
	
	/**
	* 按小区分组和小区id查询该分组下楼栋集合
	* 
	* @param  community_id     房间id
	* 		  groups		       分组信息
	* 	  
	* @return List<String>     小区楼栋集合
	*/
	public List<String> getBuildingsByGroups(String community_id,String groups);
}
