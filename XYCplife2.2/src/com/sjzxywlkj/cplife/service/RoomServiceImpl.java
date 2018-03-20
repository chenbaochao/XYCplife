package com.sjzxywlkj.cplife.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alipay.api.AlipayApiException;
import com.sjzxywlkj.cplife.alipay.request.entity.Room;
import com.sjzxywlkj.cplife.alipay.request.entity.RoominfoDelete;
import com.sjzxywlkj.cplife.alipay.request.entity.RoominfoUpload;
import com.sjzxywlkj.cplife.alipay.response.entity.PublicParameters;
import com.sjzxywlkj.cplife.alipay.response.entity.Room_info_set;
import com.sjzxywlkj.cplife.alipay.response.entity.RoominfoUploadResult;
import com.sjzxywlkj.cplife.alipay.result.RoominfoAlipay;
import com.sjzxywlkj.cplife.bean.RoomDto;
import com.sjzxywlkj.cplife.dao.CommunityMapper;
import com.sjzxywlkj.cplife.dao.RoominfoMapper;
import com.sjzxywlkj.cplife.pojo.Community;
import com.sjzxywlkj.cplife.pojo.CommunityExample;
import com.sjzxywlkj.cplife.pojo.Roominfo;
import com.sjzxywlkj.cplife.pojo.RoominfoExample;
import com.sjzxywlkj.cplife.service.RoomService;
import com.sjzxywlkj.cplife.util.BatchIdGenerator;
import com.sjzxywlkj.cplife.util.RoomScanner4Execl;
import com.sjzxywlkj.cplife.util.Thread.RoomReInsertThread;
import com.sjzxywlkj.cplife.util.excel.exception.ExcelDataException;



@Service
public class RoomServiceImpl implements RoomService {
	
	
	@Autowired
	private RoominfoMapper RoomMapper;
	@Autowired
	private CommunityMapper communityMapper;


	@Override
	public String addone(RoomDto dto) {
		// TODO Auto-generated method stub
		RoominfoUpload roominfoUpload=new RoominfoUpload();
		Map<String, Object>map=null;
		PublicParameters parameters=null;
		RoominfoUploadResult uploadResult=null;
		String batch_id=BatchIdGenerator.getLocalTrmSeqNum();
		String room_id=BatchIdGenerator.getLocalTrmSeqNum();
		roominfoUpload.setBatch_id(batch_id);
		roominfoUpload.setCommunity_id(dto.getCommunityId());
		Set<Room>set=new HashSet<Room>();
		Room room=new Room();
		room.setUnit(dto.getUnit());
		room.setOut_room_id(room_id);
		room.setBuilding(dto.getBuilding());
		room.setRoom(dto.getRoom());
		room.setAddress(dto.getAddress());
		room.setGroup(dto.getGroups());
		set.add(room);
		roominfoUpload.setRoom_info_set(set);
		try {
			map=RoominfoAlipay.upload(roominfoUpload);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "在向平台提交数据的过程中发生了未知错误，请稍后重试或联系客服";
		}
		parameters=(PublicParameters)map.get("parameters");
		if("10000".equals(parameters.getCode())){
			uploadResult=(RoominfoUploadResult)map.get("roominfoUploadResult");
			if(uploadResult.getRoom_info_set().size()!=0){
				RoomMapper.insert(new Roominfo(room_id, dto.getCommunityId(), dto.getGroups(), dto.getBuilding(),dto.getUnit(),dto.getRoom(),dto.getAddress()));
				return "添加成功";
			}
			return "数据重复，数据中已有相同详细地址的房间";
		}else{
			return parameters.getSub_msg();
		}
	}

	@Override
	public String addbyFile(String community_id,MultipartFile file) {
		
		// TODO Auto-generated method stub
		int listIndex=0;
		List<Roominfo>batchlist=new ArrayList<Roominfo>();
		Workbook workbook=null;
		Set<String>rollbackset=new HashSet<String>();
		List<Room> list=null;
		int num=0;
		try {
			InputStream in = file.getInputStream();
			String fileName=file.getOriginalFilename();
			workbook=getWorkbook(in,fileName);
			if(null==workbook){
				throw new Exception("Excel工作簿为空");
			}
			list = new RoomScanner4Execl(workbook).getRooms();
			if(list==null){
				return "文件解析失败";
			}
			num=(int)Math.ceil(list.size()/200.0);
		}catch (ExcelDataException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return e1.getMessage();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return e.getMessage();
		} 
		//
		for(int i=0;i<num;i++){
			RoominfoUpload roominfoUpload=new RoominfoUpload();
			
			Map<String, Object>map=null;
			PublicParameters parameters=null; 
			RoominfoUploadResult uploadresult=null;

			roominfoUpload.setBatch_id(BatchIdGenerator.getLocalTrmSeqNum());
			roominfoUpload.setCommunity_id(community_id);
			
			Set<Room>tempset=new HashSet<Room>();
			
			Map<String, Integer>indexMap=new HashMap<String, Integer>();
			List<Room>insert_list=list.subList(200*i, (200*(i+1)<=list.size()?200*(i+1):list.size()));
			for(Room r:insert_list){
				String temp_outroomid=BatchIdGenerator.getLocalTrmSeqNum();
				r.setOut_room_id(temp_outroomid);
				tempset.add(r);
				indexMap.put(temp_outroomid,listIndex);
				listIndex++;
			}
			
			roominfoUpload.setRoom_info_set(tempset);
			try {
				map=RoominfoAlipay.upload(roominfoUpload);
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(rollbackset!=null){
					try {
						del(rollbackset,community_id);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				return "发生了未知的网络错误";
			}
			
			uploadresult=(RoominfoUploadResult)map.get("roominfoUploadResult");
			parameters=(PublicParameters)map.get("parameters");
			if("10000".equals(parameters.getCode())){
				for(Room_info_set info_set:uploadresult.getRoom_info_set()){
					String out_room_id=info_set.getOut_room_id();
					Room add_room=list.get(indexMap.get(out_room_id));
					batchlist.add(new Roominfo(out_room_id, community_id,add_room.getGroup(),add_room.getBuilding(),add_room.getUnit(),add_room.getRoom(),add_room.getAddress()));
					rollbackset.add(out_room_id);
				}
			}else{
				try {
					if(i!=0)
						del(rollbackset,community_id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if("系统繁忙".equals(parameters.getSub_msg())){
					RoomReInsertThread insertThread=new RoomReInsertThread(community_id);
					insertThread.setPriority(4);
					insertThread.start();	 
				}			
				return "上传时发生"+ parameters.getSub_msg()+"错误，已回滚操作,请等待几分钟后再尝试上传";
			}
		}
		if(batchlist.size()>0)
			RoomMapper.insertInfoBatch(batchlist);
		return "上传成功"+batchlist.size()+"条数据";
	}

	private void del(Set<String> deleSet,String community_id) throws Exception {
		// TODO Auto-generated method stub
		if(deleSet != null && deleSet.size() > 0){
			Map<String, Object>map=null;
			//拼装向平台发送的信息
			String batch_id=BatchIdGenerator.getLocalTrmSeqNum();
			RoominfoDelete roominfoDelete=new RoominfoDelete();
			roominfoDelete.setBatch_id(batch_id);
			roominfoDelete.setCommunity_id(community_id);
			roominfoDelete.setOut_room_id_set(deleSet);
			//调用，向平台发送消息
			try {
				 map=RoominfoAlipay.delete(roominfoDelete);
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//公共相应参数
			PublicParameters parameters=(PublicParameters)map.get("parameters");
			if(!("10000".equals(parameters.getCode()))){
				throw new Exception("回滚失败");
			}
		}
	}

	//数据删除操作，返回处理信息
	@Override
	public String del(List<String> list,String community_id) {
		// TODO Auto-generated method stub
		if(list != null && list.size() > 0){
			Map<String, Object>map=null;
			//拼装向平台发送的信息
			String batch_id=BatchIdGenerator.getLocalTrmSeqNum();
			Set<String>deleteset=new HashSet<String>();
			RoominfoDelete roominfoDelete=new RoominfoDelete();
			roominfoDelete.setBatch_id(batch_id);
			roominfoDelete.setCommunity_id(community_id);
			for(String room_id:list){
				deleteset.add(room_id);
			}
			roominfoDelete.setOut_room_id_set(deleteset);
			//调用，向平台发送消息
			try {
				 map=RoominfoAlipay.delete(roominfoDelete);
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//公共相应参数
			PublicParameters parameters=(PublicParameters)map.get("parameters");
			if("10000".equals(parameters.getCode())){
				try {
					Integer delete_sum=RoomMapper.del(list);
					return "删除"+delete_sum+"条数据";
				} catch (Exception e) {
					// TODO: handle exception
					return "数据库操作错误！！！";
				}
			}else{
				return parameters.getSub_msg();
			}
		}
		return "请选择要删除的数据";
	}

	//查询
	public List<Roominfo>query(RoomDto dto,String Merchant_pid){
		if(dto.getCommunityId()==null||"".equals(dto.getCommunityId())){
			CommunityExample communityExample=new CommunityExample();
			communityExample.createCriteria().andMerchantPidEqualTo(Merchant_pid);
			List<Community>communities=communityMapper.selectByExample(communityExample);
			List<String>list=new ArrayList<String>();
			for(Community s:communities){
				list.add(s.getCommunityId());
			}
			RoominfoExample example=new RoominfoExample();
			example.createCriteria().andCommunityIdIn(list);
			return RoomMapper.selectByExample(example);
		}
		return RoomMapper.selectByExample(getExample(dto));
	}
	//支持分页的查询
	@Override
	public List<Roominfo>query(RoomDto dto,int start,int size,String Merchant_pid){
		//TODO:
		RoominfoExample example;
		if(dto.getCommunityId()==null||"".equals(dto.getCommunityId())){
			CommunityExample communityExample=new CommunityExample();
			communityExample.createCriteria().andMerchantPidEqualTo(Merchant_pid);
			List<Community>communities=communityMapper.selectByExample(communityExample);
			List<String>list=new ArrayList<String>();
			for(Community s:communities){
				list.add(s.getCommunityId());
			}
			example=new RoominfoExample();
			example.createCriteria().andCommunityIdIn(list);
		}else {
			example=getExample(dto);
		}


		example.setPageSize(size);
		example.setStart(start);
		return RoomMapper.selectByExample(example);
	}

	@Override
	public List<Roominfo> query(String cid) {
		// TODO Auto-generated method stub
		return RoomMapper.selectByCid(cid);
	}


	//获取满足条件的数据条数
	@Override
	public int countRoom(RoomDto dto,String Merchant_pid) {
		// TODO Auto-generated method stub
		if(dto.getCommunityId()==null||"".equals(dto.getCommunityId())){
			CommunityExample communityExample=new CommunityExample();
			communityExample.createCriteria().andMerchantPidEqualTo(Merchant_pid);
			List<Community>communities=communityMapper.selectByExample(communityExample);
			List<String>list=new ArrayList<String>();
			for(Community s:communities){
				list.add(s.getCommunityId());
			}
			RoominfoExample example=new RoominfoExample();
			example.createCriteria().andCommunityIdIn(list);
			return RoomMapper.countByExample(example);
		}else{
			return RoomMapper.countByExample(getExample(dto));
		}
	}

	@Override
	public List<String> getGroupsByCid(String cid) {
		// TODO Auto-generated method stub
		RoominfoExample example=new RoominfoExample();
		example.createCriteria().andCommunityIdEqualTo(cid);
		return RoomMapper.getGroupsByCid(example);
	}

	//根据小区id和分组查楼号集合
	@Override
	public List<String> getBuildingsByGroups(String cid,String groups) {
		// TODO Auto-generated method stub
		RoominfoExample example=new RoominfoExample();
		example.createCriteria().andCommunityIdEqualTo(cid);
		if(!("".equals(groups)))
			example.createCriteria().andCommunityIdEqualTo(groups);
		return RoomMapper.getBuildings(example);
	}
	@Override
	public String getAddressByOid(String out_room_id){
		return RoomMapper.getAddressByOid(out_room_id);
	}


	//根据传入的Roomdto 生成用于数据库查询的Example
	private RoominfoExample getExample(RoomDto dto){
		RoominfoExample example=new RoominfoExample();
		String groups=dto.getGroups();
		String building=dto.getBuilding();
		String community_Id=dto.getCommunityId();
		String unit=dto.getUnit();
		if(null!=groups&&!("".equals(groups))){
			example.createCriteria().andGroupsEqualTo(groups);
		}
		if(null!=building&&!("".equals(building))){
			example.createCriteria().andBuildingEqualTo(building);
		}
		if(null!=community_Id&&!("".equals(community_Id))){
			example.createCriteria().andCommunityIdEqualTo(community_Id);
		}
		if(null!=unit&&!("".equals(unit))){
			example.createCriteria().andUnitEqualTo(unit);
		}
		return example;
	}

	//根据输入流生成workbook
	public Workbook getWorkbook(InputStream in,String filename){
		Workbook wb = null;
        String fileType = filename.substring(filename.lastIndexOf("."));
        try {
        	if(".xls".equals(fileType)){
                wb = new HSSFWorkbook(in);  //2003-
            }else if(".xlsx".equals(fileType)){
                wb = new XSSFWorkbook(in);  //2007+
            }else{
                throw new Exception("解析的文件格式有误！");
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return wb;
	}

	@Override
	public String deletebyFile(String community_id, MultipartFile file) {
		// TODO Auto-generated method stub
		Workbook workbook=null;
		try {
			InputStream in = file.getInputStream();
			String fileName=file.getOriginalFilename();
			workbook=getWorkbook(in,fileName);
			if(null==workbook){
				throw new Exception("Excel工作簿为空");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return e.getMessage();
		}
		LinkedHashSet<String> set=null;
		try {
			set = new RoomScanner4Execl(workbook).getRoomId();

			if(set==null){
				return "文件解析失败";
			}if(set.size()>200){
				return "上传数据超过单次最大允许容量";
			}
		} catch (ExcelDataException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return e1.getMessage();
		}
		Map<String, Object>map=null;
		//拼装向平台发送的信息
		String batch_id=BatchIdGenerator.getLocalTrmSeqNum();

		RoominfoDelete roominfoDelete=new RoominfoDelete();
		roominfoDelete.setBatch_id(batch_id);
		roominfoDelete.setCommunity_id(community_id);

		roominfoDelete.setOut_room_id_set(set);
		//调用，向平台发送消息
		try {
			 map=RoominfoAlipay.delete(roominfoDelete);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//公共相应参数
		PublicParameters parameters=(PublicParameters)map.get("parameters");
		if("10000".equals(parameters.getCode())){
			try {
				List<String>list=new ArrayList<String>();
				for(String s:set){
					list.add(s);
				}
				Integer delete_sum=RoomMapper.del(list);
				return "删除"+delete_sum+"条数据";
			} catch (Exception e) {
				// TODO: handle exception
				return "数据库操作错误！！！";
			}
		}else{
			return parameters.getSub_msg();
		}
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		List<Roominfo>batchlist=new ArrayList<Roominfo>();

        batchlist.add(new Roominfo("201803081341470002","","","","","",""));
        batchlist.add(new Roominfo("201803081341470122","","","","","",""));
		RoomMapper.insertInfoBatch(batchlist);
	}
}