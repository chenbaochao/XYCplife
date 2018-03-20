package com.sjzxywlkj.cplife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sjzxywlkj.cplife.dao.CompanyMapper;
import com.sjzxywlkj.cplife.pojo.Company;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
    @Resource
    private CompanyMapper companyMapper;
    
    public void setCompanyMapper(CompanyMapper companyMapper) {
		this.companyMapper = companyMapper;
	}
    
	//查询物业公司
    @RequestMapping("/selectBySpreadId")
    @ResponseBody
    public List<Company> select(String spreadId){
    	List<Company>companyList= companyMapper.selectByPrimaryKeySpread(spreadId);
        if(companyList==null){
        	return new ArrayList<Company>();
        }
		return companyList;
    }
	//        查询物业公司
	@RequestMapping("/selectByDistrictCode")
	@ResponseBody
	public List<Company> selectl(String districtCode){
	    List<Company> companyList= companyMapper.selectByPrimaryKeyDistrict_code(districtCode);
	    
	    if(companyList==null){
        	return new ArrayList<Company>();
        }
		return companyList;
	}
    //    增加物业公司
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(Company company) {
        Company oldcompany=companyMapper.selectByPrimaryKey(company.getMerchantPid());
        /*if(record.getMerchantPid().equals(oldcompany.getMerchantPid())&&record.getCompanyName().equals(oldcompany.getCompanyName())&&
                record.getDistrictCode().equals(oldcompany.getDistrictCode())&&record.getSpreadId().equals(oldcompany.getSpreadId())){
            */
        
        /*if(oldcompany!=null){
        	return "已存在该物业公司";
        }*/
        companyMapper.insert(company);
        return "添加成功";
    }
    //删除物业公司
    @RequestMapping("/delect")
    @ResponseBody
    public  String deleteByPrimaryKey(String merchantPid) {
        companyMapper.deleteByPrimaryKey(merchantPid);
        return "success";
    }
	@RequestMapping("/update")
	@ResponseBody
	public String doupdate(Company record, Model model){
	    Company oldcompany=companyMapper.selectByPrimaryKey(record.getMerchantPid());
	    if(record.getMerchantPid().equals(oldcompany.getMerchantPid())&&record.getCompanyName().equals(oldcompany.getCompanyName())&&
	            record.getDistrictCode().equals(oldcompany.getDistrictCode())&&record.getSpreadId().equals(oldcompany.getSpreadId())){
	    	return "信息未修改";
	    }
	    companyMapper.updateByPrimaryKey(record);
	    return "修改成功";
	}
}

