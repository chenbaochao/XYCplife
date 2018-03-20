package com.sjzxywlkj.cplife.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.sjzxywlkj.cplife.pojo.Bill;

public interface IBillService {

	String addOne(Bill bill);
	String addAll(Set<Bill> bills);
	String update(Bill bill);
	void delet(Set<String> bills);
	String delete(String id);
	String selectById(String id);
	List<Bill> selectAll(String groups,String building,String unit,String room,String address,String community_id,String out_room_id,String cost_type,String acct_period,String release_day,String deadline,String status);
	void offlinePayment(String id);
	void offlinePayment(Set<String> idSet);
	void onlinePayment(String id);
	Map<String, List<String>> choose(String id) throws JsonGenerationException, JsonMappingException, IOException;
}
