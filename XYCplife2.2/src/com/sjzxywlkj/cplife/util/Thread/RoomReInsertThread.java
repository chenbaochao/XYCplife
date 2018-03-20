package com.sjzxywlkj.cplife.util.Thread;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;

import com.sjzxywlkj.cplife.alipay.request.entity.RoominfoDelete;
import com.sjzxywlkj.cplife.alipay.request.entity.RoominfoQuery;
import com.sjzxywlkj.cplife.alipay.response.entity.Room_info;
import com.sjzxywlkj.cplife.alipay.response.entity.RoominfoQueryResult;
import com.sjzxywlkj.cplife.alipay.result.RoominfoAlipay;
import com.sjzxywlkj.cplife.dao.RoominfoMapper;
import com.sjzxywlkj.cplife.pojo.Roominfo;
import com.sjzxywlkj.cplife.pojo.RoominfoExample;
import com.sjzxywlkj.cplife.util.BatchIdGenerator;

public class RoomReInsertThread extends Thread {
	@Autowired
	RoominfoMapper roominfoMapper;
	
	private String community_id;
	
	public RoomReInsertThread(String community_id) {
		// TODO Auto-generated constructor stub
		this.community_id=community_id;
	}
	
	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		try {
			sleep(1000*60);
			
			RoominfoQuery roominfoQuery=new RoominfoQuery();
			roominfoQuery.setCommunity_id(community_id);
			Map<String, Object> querymap=RoominfoAlipay.query(roominfoQuery);

			RoominfoQueryResult roominfoQueryResult=(RoominfoQueryResult)querymap.get("roominfoQueryResult");
			Integer totalnumber=roominfoQueryResult.getTotal_room_number();
			RoominfoExample example=new RoominfoExample();
			example.createCriteria().andCommunityIdEqualTo(community_id);
			List<Roominfo>rooms=roominfoMapper.selectByExample(example);
			
			if(rooms.size()==totalnumber){
				return;
			}
			
			
			Set<String>db_id_set=new HashSet<String>();
			for(Roominfo r:rooms){
				db_id_set.add(r.getOutRoomId());
			}
			
			Set<String>set=new HashSet<String>();
			int pageCount=(int)Math.ceil(totalnumber/200.0);
			for(int i=0;i<pageCount;i++){
				RoominfoQuery query=new RoominfoQuery();
				query.setCommunity_id(community_id);
				query.setPage_num(i+1);
				Map<String, Object> resultmap=RoominfoAlipay.query(query);
				RoominfoQueryResult	roomresult=(RoominfoQueryResult)resultmap.get("roominfoQueryResult");
				List<Room_info> roomlist=roomresult.getRoom_info();
				for(Room_info room:roomlist){
					set.add(room.getOut_room_id());
				}
			}
			set.removeAll(db_id_set);
			if(set.size()>0){
				RoominfoDelete roominfoDelete=new RoominfoDelete();
				roominfoDelete.setBatch_id(BatchIdGenerator.getLocalTrmSeqNum());
				roominfoDelete.setCommunity_id(community_id);
				roominfoDelete.setOut_room_id_set(set);
				
				Map<String, Object>map=RoominfoAlipay.delete(roominfoDelete);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}catch(Exception e){
			return;
		}
	}
	
	
}
