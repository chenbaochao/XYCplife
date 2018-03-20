package com.sjzxywlkj.cplife.dao;

import com.sjzxywlkj.cplife.pojo.Spread;
import java.util.List;

public interface SpreadMapper {
	int deleteByPrimaryKey(String id);

	int insert(Spread record);

	Spread selectByPrimaryKey(String id);

	List<Spread> selectAll();

	int updateByPrimaryKey(Spread record);

	void updateSpreadById(Spread spread);
}