package com.sjzxywlkj.cplife.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sjzxywlkj.cplife.pojo.Bill;

public interface IBillDao {

	void insertBill(Bill bill);
	void deleteById(String id);
	void updateBill(Bill bill);
	void offlinePayment(String id);
	void onlinePayment(String id);
	Bill selectById(String id);
	List<Bill> selectAll(@Param("groups")String groups,@Param("building")String building,@Param("unit")String unit,@Param("room")String room,@Param("address")String address,@Param("community_id")String community_id,@Param("out_room_id")String out_room_id,@Param("cost_type")String cost_type,@Param("acct_period")String acct_period,@Param("release_day")String release_day,@Param("deadline")String deadline,@Param("status")String status);
	List<String> cost_type(String id);
	/*List<String> bill_entry_amount(String id);*/
	List<String> acct_period(String id);
	List<String> release_day(String id);
	List<String> deadline(String id);
	
}
