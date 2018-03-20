package com.sjzxywlkj.cplife.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjzxywlkj.cplife.bean.ProfitShow;
import com.sjzxywlkj.cplife.service.IProfitService;

@Controller
@RequestMapping("/profit")
public class ProfitController{
	
	@Autowired
	@Qualifier("profitService")
	private IProfitService profitService;

	public void setProfitService(IProfitService profitService) {
		this.profitService = profitService;
	}
	
	@RequestMapping(value="/inquireByCompany")
	@ResponseBody
	public ProfitShow inquireByCompany(String id) throws JsonGenerationException, JsonMappingException, IOException{
		return profitService.inquireByCompany(id);
	}
	@RequestMapping(value="/inquireByCommunity")
	@ResponseBody
	public ProfitShow inquireByCommunity(String id) throws JsonGenerationException, JsonMappingException, IOException{
		return profitService.inquireByCommunity(id);
	}
	@RequestMapping(value="/inquireByspread")
	@ResponseBody
	public ProfitShow inquireByspread(String id) throws JsonGenerationException, JsonMappingException, IOException{
		return profitService.inquireByspread(id);
	}
	
}
