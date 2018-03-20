package com.sjzxywlkj.cplife.dao;

import java.util.List;
import com.sjzxywlkj.cplife.pojo.AdminNum;

public interface AdminNumMapper {
    int deleteByPrimaryKey(String id);

    int insert(AdminNum record);

    AdminNum selectByPrimaryKey(String id);

    List<AdminNum> selectAll();

    int updateByPrimaryKey(AdminNum record);

    Integer count(AdminNum adminNum);
}