package com.sjzxywlkj.cplife.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sjzxywlkj.cplife.dao.IBillDao;
import com.sjzxywlkj.cplife.pojo.Bill;

@Service("billService")
public class BillService implements IBillService {
	
	@Resource(name="IBillDao")
	private IBillDao billDao;
	
	public void setBillDao(IBillDao billDao) {
		this.billDao = billDao;
	}

	@Override
	public String addOne(Bill bill) {
		// TODO Auto-generated method stub
		billDao.insertBill(bill);
		return null;
	}

	@Override
	public String update(Bill bill) {
		// TODO Auto-generated method stub
		billDao.updateBill(bill);
		return null;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		billDao.deleteById(id);
		return null;
	}

	@Override
	public String selectById(String id) {
		// TODO Auto-generated method stub
		billDao.selectById(id);
		return null;
	}

	@Override
	public List<Bill> selectAll(String groups,String building,String unit,String room,String address,String community_id,String out_room_id,String cost_type,String acct_period,String release_day,String deadline,String status){
		// TODO Auto-generated method stub
		return billDao.selectAll(groups, building, unit, room, address, community_id, out_room_id, cost_type, acct_period, release_day, deadline, status);
	}

	@Override
	public String addAll(Set<Bill> bills) {
		// TODO Auto-generated method stub
		Iterator<Bill> it = bills.iterator();
		while (it.hasNext()) {
			Bill bill = (Bill) it.next();
			billDao.insertBill(bill);
		}
		return null;
	}

	@Override
	public void delet(Set<String> bills) {
		// TODO Auto-generated method stub
		Iterator<String> iterator = bills.iterator();
		while (iterator.hasNext()) {
			String id = (String) iterator.next();
			billDao.deleteById(id);
		}
		
	}

	@Override
	public void offlinePayment(String id) {
		// TODO Auto-generated method stub
		billDao.offlinePayment(id);
	}

	@Override
	public void onlinePayment(String id) {
		// TODO Auto-generated method stub
		billDao.onlinePayment(id);
	}

	@Override
	public void offlinePayment(Set<String> idSet) {
		// TODO Auto-generated method stub
		Iterator<String> iterator = idSet.iterator();
		while (iterator.hasNext()) {
			String id = (String) iterator.next();
			billDao.offlinePayment(id);
		}
	}

	@Override
	public Map<String, List<String>> choose(String id){
		// TODO Auto-generated method stub
		Map<String, List<String>> choose = new HashMap<String, List<String>>();
		choose.put("cost_type", billDao.cost_type(id));
		choose.put("acct_period", billDao.acct_period(id));
		choose.put("release_day", billDao.release_day(id));
		choose.put("deadline", billDao.deadline(id));
		
		return choose;
	}

}
