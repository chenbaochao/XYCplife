package com.sjzxywlkj.cplife.pojo;

public class Bill {

	private String bill_entry_id;
	private String community_id;
	private String out_room_id;
	private String cost_type;
	private String bill_entry_amount;
	private String acct_period;
	private String release_day;
	private String deadline;
	private String status;
	public String getBill_entry_id() {
		return bill_entry_id;
	}
	public void setBill_entry_id(String bill_entry_id) {
		this.bill_entry_id = bill_entry_id;
	}
	public String getCommunity_id() {
		return community_id;
	}
	public void setCommunity_id(String community_id) {
		this.community_id = community_id;
	}
	public String getOut_room_id() {
		return out_room_id;
	}
	public void setOut_room_id(String out_room_id) {
		this.out_room_id = out_room_id;
	}
	public String getCost_type() {
		return cost_type;
	}
	public void setCost_type(String cost_type) {
		this.cost_type = cost_type;
	}
	public String getBill_entry_amount() {
		return bill_entry_amount;
	}
	public void setBill_entry_amount(String bill_entry_amount) {
		this.bill_entry_amount = bill_entry_amount;
	}
	public String getAcct_period() {
		return acct_period;
	}
	public void setAcct_period(String acct_period) {
		this.acct_period = acct_period;
	}
	public String getRelease_day() {
		return release_day;
	}
	public void setRelease_day(String release_day) {
		this.release_day = release_day;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Bill(String bill_entry_id, String community_id, String out_room_id, String cost_type,
			String bill_entry_amount, String acct_period, String release_day, String deadline, String status) {
		super();
		this.bill_entry_id = bill_entry_id;
		this.community_id = community_id;
		this.out_room_id = out_room_id;
		this.cost_type = cost_type;
		this.bill_entry_amount = bill_entry_amount;
		this.acct_period = acct_period;
		this.release_day = release_day;
		this.deadline = deadline;
		this.status = status;
	}
	public Bill() {
		super();
	}
	@Override
	public String toString() {
		return "Bill [bill_entry_id=" + bill_entry_id + ", community_id=" + community_id + ", out_room_id="
				+ out_room_id + ", cost_type=" + cost_type + ", bill_entry_amount=" + bill_entry_amount
				+ ", acct_period=" + acct_period + ", release_day=" + release_day + ", deadline=" + deadline
				+ ", status=" + status + "]";
	}
	
	
}
