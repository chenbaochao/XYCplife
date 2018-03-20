package com.sjzxywlkj.cplife.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sjzxywlkj.cplife.bean.RoomDto;
import com.sjzxywlkj.cplife.pojo.Roominfo;
import com.sjzxywlkj.cplife.service.RoomService;
import com.sjzxywlkj.cplife.util.Data2Excel;

import com.sjzxywlkj.cplife.util.Page;
import com.sjzxywlkj.cplife.util.excel.ViewExcel;
import com.sjzxywlkj.cplife.util.excel.templet.RoomInfoTemplet;
import com.alibaba.fastjson.JSONArray;


/**
* 房间管理模块功能控制层
* @author: shulin
* @date:   2018/3/10
* @tel:    15732157252
*/

@Controller
@RequestMapping("/room")
public class RoomController {
	@Autowired
	private RoomService roomservice;
	
	/**
	 * 添加房屋（单个）  
	 * @param dto	房间信息传输对象
	 * @return	执行结果信息
	 */
	@RequestMapping(value="/addOne",method=RequestMethod.POST)
	@ResponseBody
	public String addone(@RequestBody RoomDto dto) {
      	return roomservice.addone(dto);
    }
	
	
	/**
	 * 添加房屋（按传入Excel文件添加）  
	 * @param dto	房间信息传输对象
	 * @
	 * @return	执行结果信息
	 */
	@RequestMapping(value="/addByFile",method=RequestMethod.POST)
	@ResponseBody
	public String addbyfile(MultipartFile file,String communityid){ 
		//TODO:从前台获取communityid，整合完成后去除"A6X8LUMI84403"
		//		改为从前台获取的communityid
		return roomservice.addbyFile("A6X8LUMI84403", file);
	}
	
	@RequestMapping(value="/deleteByFile",method=RequestMethod.POST)
	@ResponseBody
	public String deletebyfile(MultipartFile file,String communityid){
	    if(file==null){
	    	return "请传入EXCEL文件";
	    }
	  //TODO:从前台获取communityid，整合完成后去除"A6X8LUMI84403"
	  //	  改为从前台获取的communityid
		return roomservice.deletebyFile("A6X8LUMI84403",file);	
	}

	@ResponseBody
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public String del(@RequestBody Map<String,Object> map){
		List<String> deleteid = (List<String>) JSONArray.parseArray(String.valueOf(map.get("deleteid")), String.class);
		String.valueOf(map.get("communityid"));
		if(deleteid==null||deleteid.size()==0){
			return  "请选择要删除的数据";
		}
		/*TODO:从前台获取communityid，整合完成后去除"A6X8LUMI84403"
			  改为从前台获取的communityid
		代码：String communityid=String.valueOf(map.get("communityid"));*/
		
		return roomservice.del(deleteid,"A6X8LUMI84403");
	}
	
	
	@ResponseBody
	@RequestMapping(value="/queryByPage",method=RequestMethod.POST)
	public Page<RoomDto> queryRoomByPage(@RequestBody Map<String,Object> map) throws JsonGenerationException, JsonMappingException, IOException{	
		Page<RoomDto>page=new Page<RoomDto>();
		page.setPagesize(Integer.parseInt((map.get("pageSize").toString())));
		page.setCurrentpage(Integer.parseInt((map.get("pageNumber").toString())));
		RoomDto dto=new RoomDto();
		//TODO:整合完成后取消注释
		//String communityId=(null==map.get("communityId")?null:map.get("communityId").toString());
		String groups=(null==map.get("groups")?null:map.get("groups").toString());
		String building=(null==map.get("building")?null:map.get("building").toString());
		String unit=(null==map.get("unit")?null:map.get("unit").toString());
		dto.setGroups(groups);
		dto.setBuilding(building);
		dto.setUnit(unit);
		dto.setCommunityId("A6X8LUMI84403");
		//TODO::从前台获取communityid，整合完成后去除"A6X8LUMI84403"和本行注释
		//dto.setCommunityId(map.get("communityid").toString());
		int total=roomservice.countRoom(dto,null);
		page.setTotal(total);
		
		//TODO:从Session中获取物业公司merchant_pid,添加至query方法中
		List<Roominfo> queryresult=roomservice.query(dto,page.getStart(),page.getPagesize(),null);
		List<RoomDto> rows=new ArrayList<RoomDto>();	
		for(Roominfo roominfo:queryresult){
			RoomDto roomDto=new RoomDto();
			BeanUtils.copyProperties(roominfo,roomDto);	
			rows.add(roomDto);
		}
		page.setRows(rows);
		return page;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/queryRooms",method=RequestMethod.POST)
	public List<RoomDto> queryRoom(@RequestBody RoomDto dto) throws JsonGenerationException, JsonMappingException, IOException{
		//TODO:从Session中获取物业公司merchant_pid,添加至query方法中
		List<Roominfo> queryresult=roomservice.query(dto,null);
		List<RoomDto> rows=new ArrayList<RoomDto>();
		for(Roominfo roominfo:queryresult){
			RoomDto roomDto=new RoomDto();
			BeanUtils.copyProperties(roominfo,roomDto);
			rows.add(roomDto);
		}	
		return rows;
	}
	
	@ResponseBody
	@RequestMapping(value="/getGroupsByCid",method=RequestMethod.POST)
	public List<String> getGroupsByCid(String community_id){
		List<String>groups=roomservice.getGroupsByCid(community_id);
		return groups;
	}
	
	@ResponseBody
	@RequestMapping(value="/getBuildings",method=RequestMethod.POST)
	public List<String> getBuildings(String community_id,String groups){
		List<String>buildings=roomservice.getBuildingsByGroups(community_id, groups);
		return buildings;
	}
	
	
	@RequestMapping(value="/export2Excel",method=RequestMethod.POST)
	public ModelAndView export2excel(ModelMap model,HttpServletResponse response,HttpServletRequest request, RoomDto dto) throws IOException{
		//生成Excel表的实现类
		Data2Excel data2Excel=new Data2Excel();
		//生成数据
		List<Roominfo> queryresult=roomservice.query(dto,null);
		//调用方法生成HSSFWorkbook
		HSSFWorkbook workbook = data2Excel.generateExcel(queryresult,RoomInfoTemplet.getTemplet(), "人员信息表");
		ViewExcel viewExcel = new ViewExcel();            
        //获取数据库表生成的workbook    
        try {    
         viewExcel.buildExcelDocument(null, workbook, request, response);    
        } catch (Exception e) {    
      // TODO Auto-generated catch block    
            e.printStackTrace();    
        }    
        return new ModelAndView(viewExcel, model);     			
	}

	//获取excel表格文件
	@RequestMapping(value="/download")
	public void download(@RequestParam(value="filename") String fileName,
						 HttpSession session,
						 HttpServletResponse response) throws UnsupportedEncodingException{
		//判断要下载的模板
		if("insert".equals(fileName)||("delete".equals(fileName))){
			//下载上传文件路径
			String path = session.getServletContext().getRealPath("/WEB-INF/excel");
			String file_name=null;
			if(fileName.equals("delete")){
				file_name = new String(("批量删除模板.xlsx").getBytes("GB2312"),"ISO-8859-1");
			}else{
				file_name = new String(("批量添加模板.xlsx").getBytes("GB2312"),"ISO-8859-1");
			}
			response.setContentType("application/octet-stream");
			response.setHeader("content-disposition", 
								"attachment;filename=" + file_name);
			try(InputStream is = new FileInputStream(new File(path,"room_"+fileName+".xlsx"));
				OutputStream out=response.getOutputStream();) {
				//读取byte数组保存二进制信息
				byte[] b = new byte[is.available()];
				//开始读取下载信息并写出
				while(is.read(b) != -1){
					out.write(b);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}