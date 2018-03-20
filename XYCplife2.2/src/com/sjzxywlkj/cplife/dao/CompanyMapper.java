package com.sjzxywlkj.cplife.dao;


import java.util.List;

import com.sjzxywlkj.cplife.pojo.Company;

public interface CompanyMapper {

    int deleteByPrimaryKey(String merchantPid);

    int insert(Company record);

    Company selectByPrimaryKey(String merchantPid);

    List<Company> selectAll();

    int updateByPrimaryKey(Company record);

    List<Company> selectByPrimaryKeySpread(String spreadId);

    List<Company> selectByPrimaryKeyDistrict_code(String districtCode);
}