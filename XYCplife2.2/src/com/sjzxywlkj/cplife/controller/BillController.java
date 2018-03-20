package com.sjzxywlkj.cplife.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.sjzxywlkj.cplife.alipay.request.entity.BillBatchupload;
import com.sjzxywlkj.cplife.alipay.request.entity.BillDelete;
import com.sjzxywlkj.cplife.alipay.request.entity.Bill_set;
import com.sjzxywlkj.cplife.alipay.response.entity.Alive_bill_entry_list;
import com.sjzxywlkj.cplife.alipay.response.entity.BillBatchuploadResult;
import com.sjzxywlkj.cplife.alipay.response.entity.BillDeleteResult;
import com.sjzxywlkj.cplife.alipay.response.entity.PublicParameters;
import com.sjzxywlkj.cplife.alipay.result.BillAlipay;
import com.sjzxywlkj.cplife.bean.BillShow;
import com.sjzxywlkj.cplife.pojo.Bill;
import com.sjzxywlkj.cplife.pojo.Roominfo;
import com.sjzxywlkj.cplife.service.BillService;
import com.sjzxywlkj.cplife.service.RoomService;
import com.sjzxywlkj.cplife.util.DownUtil;
import com.sjzxywlkj.cplife.util.ExcelUtil;
import com.sjzxywlkj.cplife.util.NumUtil;


@Controller
@RequestMapping("/bill")
public class BillController{
	
	@Autowired
	private RoomService roomService;
	@Autowired
	@Qualifier("billService")
	private BillService bService;
	
	
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	
	public void setbService(BillService bService) {
		this.bService = bService;
	}
	//账单上传模板下载
	@RequestMapping(value="/AddCaseDown")
	public void billAddCaseDown(String community_id,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		
		//调用查询
		List<Roominfo> rooms = roomService.query(community_id);
		
		String path = session.getServletContext().getRealPath("/WEB-INF/excel");
		ByteArrayOutputStream out = ExcelUtil.billCaseDown(rooms,path);
        String fileName = "添加账单模板.xlsx";
        DownUtil.down(fileName, out, request, response);
		
	}
	@RequestMapping(value="/AddUpload")
	@ResponseBody
	public String billAddUpload(String community_id,@RequestParam MultipartFile file) throws IOException{
		
		InputStream io = null;
		
		if (!file.isEmpty()) {
			io = file.getInputStream();
			Set<Bill> bSets = null;

			try {
				bSets = ExcelUtil.uploadBill(io, community_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				if (bSets==null) {
					 return "文件上传失败或读取失败,请稍后重试";
				}else {
					Set<Bill_set> bill_sets = new HashSet<Bill_set>();
					Iterator<Bill> bIterable = bSets.iterator();
					while (bIterable.hasNext()) {
						Bill bill = (Bill) bIterable.next();
						Bill_set bill_set = new Bill_set(bill.getBill_entry_id(), bill.getOut_room_id(), null, bill.getCost_type(), bill.getBill_entry_amount(), bill.getAcct_period(), bill.getRelease_day(), bill.getDeadline(), null, null);
						bill_sets.add(bill_set);
					}
					String bill_batch_id = NumUtil.getBill_batch_id();
					BillBatchupload batchupload = new BillBatchupload(bill_batch_id, community_id, bill_sets);
					Map<String, Object> result = null;
					try {
						result = BillAlipay.batchUpload(batchupload);
					} catch (AlipayApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (result!=null) {
						PublicParameters parameters = (PublicParameters) result.get("parameters");
						BillBatchuploadResult billBatchuploadResult = (BillBatchuploadResult) result.get("billBatchuploadResult");
						if (parameters.getCode().equals("10000")&&billBatchuploadResult.getBatch_id().equals(bill_batch_id)) {
							bService.addAll(bSets);
							return "上传成功";
						}else{
							return parameters.getSub_msg();
						}
					}else {
						return "文件上传失败或读取失败,请稍后重试";
					}
				}
			
		}else {
			return "文件上传失败";
		}
	}
	//账单文件下载
	@RequestMapping(value="/Down")
	public void billDown(String community_name,String groups,String building,String unit,String room,String address,String community_id,String out_room_id,String cost_type,String acct_period,String release_day,String deadline,String status,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		
		List<Bill> bList = bService.selectAll(groups, building, unit, room, address, community_id, out_room_id, cost_type, acct_period, release_day, deadline, status);
		List<BillShow> bShows = new ArrayList<BillShow>();
		for (int i = 0; i < bList.size(); i++) {
			Bill bill2 = bList.get(i);
			//获取房间
			BillShow billShow = new BillShow(bill2.getBill_entry_id(), bill2.getCommunity_id(), bill2.getOut_room_id(), bill2.getCost_type(), bill2.getBill_entry_amount(), bill2.getAcct_period(), bill2.getRelease_day(), bill2.getDeadline(), bill2.getStatus(), community_name, roomService.getAddressByOid(bill2.getOut_room_id()));
			bShows.add(billShow);
		}
		String path = session.getServletContext().getRealPath("/WEB-INF/excel");
		ByteArrayOutputStream out = ExcelUtil.billDown(bShows,path);
		String fileName = "账单.xlsx";
        DownUtil.down(fileName, out, request, response);
	}
	//删除账单文件上传
	@RequestMapping(value="/Del")
	@ResponseBody
	public String billDel(String community_id,@RequestParam MultipartFile file) throws IOException{

		InputStream io = null;
		if (!file.isEmpty()) {
			io = file.getInputStream();
			Set<String> bill_entry_id_list = null;
			try {
				bill_entry_id_list = ExcelUtil.billDel(io);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if (bill_entry_id_list==null) {
				return "文件上传失败或读取失败,请稍后重试";
			}else {
				BillDelete billDelete = new BillDelete(community_id, bill_entry_id_list);
				System.out.println(JSON.toJSONString(billDelete));
				
				Map<String, Object> result = null;
				try {
					result = BillAlipay.delete(billDelete);
				} catch (AlipayApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (result!=null) {
					PublicParameters parameters = (PublicParameters) result.get("parameters");
					BillDeleteResult billDeleteResult = (BillDeleteResult) result.get("billDeleteResult");
					
					if (parameters.getCode().equals("10000")) {
						if (billDeleteResult.getAlive_bill_entry_list()==null) {
							bService.delet(bill_entry_id_list);
							return "删除成功";
						}else {
							
							Iterator<Alive_bill_entry_list> iterator = billDeleteResult.getAlive_bill_entry_list().iterator();
							while (iterator.hasNext()) {
								Alive_bill_entry_list alive_bill_entry_list = (Alive_bill_entry_list) iterator.next();
								bill_entry_id_list.remove(alive_bill_entry_list.getBill_entry_id());
							}
							bService.delet(bill_entry_id_list);
							
							return "不允许删除条目："+JSON.toJSONString(billDeleteResult.getAlive_bill_entry_list());
						}
						
						
					}else {
						return parameters.getSub_msg();
					}
				}else{
					return "文件上传失败或读取失败,请稍后重试";
				}
			}
			
		}else {
			return "文件上传错误";
		}
        
	}
	
	//线下缴费账单文件上传
	@RequestMapping(value="/UpdateUpload")
	@ResponseBody
	public String billUpdateUpload(String community_id,@RequestParam MultipartFile file) throws IOException{
		
		InputStream io = null;
		if (!file.isEmpty()) {
			io = file.getInputStream();
			Set<String> bill_entry_id_list = null;
			try {
				bill_entry_id_list = ExcelUtil.billDel(io);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (bill_entry_id_list==null) {
				 return"文件上传失败或读取失败";
			}else {
				
				bService.offlinePayment(bill_entry_id_list);
				return "更改成功";
			}
		}else {
			return "文件上传错误";
		}
        
	}
	
	//线下收款
	@RequestMapping(value="/Update")
	@ResponseBody
	public String billUpdate(String bill_entry_id){
		bService.offlinePayment(bill_entry_id);
		return "更改成功";
	}
	//账单查询
	@RequestMapping(value="/Show")
	@ResponseBody
	public List<BillShow> billShow(String groups,String building,String unit,String room,String address,String community_id,String out_room_id,String cost_type,String acct_period,String release_day,String deadline,String status) throws JsonGenerationException, JsonMappingException, IOException{
		
		List<Bill> bList = bService.selectAll(groups, building, unit, room, address, community_id, out_room_id, cost_type, acct_period, release_day, deadline, status);
		List<BillShow> bShows = new ArrayList<BillShow>();
		for (int i = 0; i < bList.size(); i++) {
			Bill bill2 = bList.get(i);
			//获取房间
			BillShow billShow = new BillShow(bill2.getBill_entry_id(), bill2.getCommunity_id(), bill2.getOut_room_id(), bill2.getCost_type(), bill2.getBill_entry_amount(), bill2.getAcct_period(), bill2.getRelease_day(), bill2.getDeadline(), bill2.getStatus(), null, roomService.getAddressByOid(bill2.getOut_room_id()));
			bShows.add(billShow);
		}
		return bShows;
	}
	
	//账单筛选条件
	@RequestMapping(value="/Choose")
	@ResponseBody
	public Map<String, List<String>> billChoose(String community_id){
		return bService.choose(community_id);
	}
	
}
