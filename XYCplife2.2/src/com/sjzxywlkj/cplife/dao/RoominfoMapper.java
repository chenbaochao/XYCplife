package com.sjzxywlkj.cplife.dao;

import java.util.List;

import com.sjzxywlkj.cplife.pojo.Roominfo;
import com.sjzxywlkj.cplife.pojo.RoominfoExample;

public interface RoominfoMapper {
    int deleteByPrimaryKey(String outRoomId);

    int insert(Roominfo record);

    int insertSelective(Roominfo record);

    List<Roominfo> selectByExample(RoominfoExample example);
    List<Roominfo> selectByCid(String _parameter);
    Roominfo selectByPrimaryKey(String outRoomId);

    int updateByPrimaryKeySelective(Roominfo record);

    int updateByPrimaryKey(Roominfo record);
    void insertInfoBatch(List<Roominfo> batchlist);
    int countByExample(RoominfoExample example);
    public int del(List<String> list);
    String getAddressByOid(String _parameter);
	List<String> getGroupsByCid(RoominfoExample example);
	List<String> getBuildings(RoominfoExample example);
}