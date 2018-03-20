package com.sjzxywlkj.cplife.service;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.sjzxywlkj.cplife.bean.ProfitShow;

public interface IProfitService {
	
	ProfitShow inquireByCompany(String id) throws JsonGenerationException, JsonMappingException, IOException;
	ProfitShow inquireByCommunity(String id) throws JsonGenerationException, JsonMappingException, IOException;
	ProfitShow inquireByspread(String id) throws JsonGenerationException, JsonMappingException, IOException;
	void add(String id);
}
