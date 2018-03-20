package com.sjzxywlkj.cplife.bean;

import com.sjzxywlkj.cplife.pojo.Bill;

public class BillShow extends Bill{

	private String community_name;
	private String room_address;
	public String getCommunity_name() {
		return community_name;
	}
	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}
	public String getRoom_address() {
		return room_address;
	}
	public void setRoom_address(String room_address) {
		this.room_address = room_address;
	}
	public BillShow(String bill_entry_id, String community_id, String out_room_id, String cost_type,
			String bill_entry_amount, String acct_period, String release_day, String deadline, String status,
			String community_name, String room_address) {
		super(bill_entry_id, community_id, out_room_id, cost_type, bill_entry_amount, acct_period, release_day,
				deadline, status);
		this.community_name = community_name;
		this.room_address = room_address;
	}
	public BillShow(String bill_entry_id, String community_id, String out_room_id, String cost_type,
			String bill_entry_amount, String acct_period, String release_day, String deadline, String status) {
		super(bill_entry_id, community_id, out_room_id, cost_type, bill_entry_amount, acct_period, release_day,
				deadline, status);
	}
	@Override
	public String toString() {
		return "BillShow [community_name=" + community_name + ", room_address=" + room_address + "]";
	}
	
}
