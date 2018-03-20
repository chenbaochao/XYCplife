package com.sjzxywlkj.cplife.dao;

import java.util.List;

import com.sjzxywlkj.cplife.pojo.Profit;

public interface IProfitDao {
	
	void add(String id);
	void insertProfit(Profit profit);
	void deleteProfit(String id);
	void updateProfit(Profit profit);
	Profit selectByCommunityId(String id);
	List<Profit> selectByCompanyID(String id);
	List<Profit> selectBySpreadID(String id);
}
