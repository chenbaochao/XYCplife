package com.sjzxywlkj.cplife.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.sjzxywlkj.cplife.bean.ProfitShow;
import com.sjzxywlkj.cplife.dao.IProfitDao;
import com.sjzxywlkj.cplife.pojo.Profit;


@Service("profitService")
public class ProfitService implements IProfitService{
	
	@Resource(name="IProfitDao")
	private IProfitDao profitDao;

	public void setProfitDao(IProfitDao profitDao) {
		this.profitDao = profitDao;
	}

	@Override
	public ProfitShow inquireByCompany(String id) throws JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		List<Profit> pList = profitDao.selectByCompanyID(id);
		if (pList==null) {
			return null;
		} else {
			Iterator<Profit> iterator = pList.iterator();
			Profit proSum = new Profit();
			while (iterator.hasNext()) {
				Profit profit = (Profit) iterator.next();
				//此处报空指针，请检查业务逻辑
				//TODO:*****************************************
				//**********************************************
				proSum.setJan(proSum.getJan().add(profit.getJan()));
				proSum.setFeb(proSum.getFeb().add(profit.getFeb()));
				proSum.setMar(proSum.getMar().add(profit.getMar()));
				proSum.setApr(proSum.getApr().add(profit.getApr()));
				proSum.setMay(proSum.getMay().add(profit.getMay()));
				proSum.setJun(proSum.getJun().add(profit.getJun()));
				proSum.setJul(proSum.getJul().add(profit.getJul()));
				proSum.setAug(proSum.getAug().add(profit.getAug()));
				proSum.setSep(proSum.getSep().add(profit.getSep()));
				proSum.setOct(proSum.getOct().add(profit.getOct()));
				proSum.setNov(proSum.getNov().add(profit.getNov()));
				proSum.setDece(proSum.getDece().add(profit.getDece()));
	
			}
			return new ProfitShow(proSum.month().toString(), proSum.lastMonth().toString(), proSum.quarterly().toString(), proSum.lastQuarterly().toString());

		}
	}

	@Override
	public ProfitShow inquireByCommunity(String id) throws JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		Profit proSum = profitDao.selectByCommunityId(id);
		if (proSum==null) {
			return null;
		} else {
			return new ProfitShow(proSum.month().toString(), proSum.lastMonth().toString(), proSum.quarterly().toString(), proSum.lastQuarterly().toString());
			
		}
		
	}

	@Override
	public ProfitShow inquireByspread(String id) throws JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		List<Profit> pList = profitDao.selectBySpreadID(id);
		if (pList==null) {
			return null;
		} else {
			Iterator<Profit> iterator = pList.iterator();
			Profit proSum = new Profit();
			while (iterator.hasNext()) {
				Profit profit = (Profit) iterator.next();
				
				proSum.setJan(proSum.getJan().add(profit.getJan()));
				proSum.setFeb(proSum.getFeb().add(profit.getFeb()));
				proSum.setMar(proSum.getMar().add(profit.getMar()));
				proSum.setApr(proSum.getApr().add(profit.getApr()));
				proSum.setMay(proSum.getMay().add(profit.getMay()));
				proSum.setJun(proSum.getJun().add(profit.getJun()));
				proSum.setJul(proSum.getJul().add(profit.getJul()));
				proSum.setAug(proSum.getAug().add(profit.getAug()));
				proSum.setSep(proSum.getSep().add(profit.getSep()));
				proSum.setOct(proSum.getOct().add(profit.getOct()));
				proSum.setNov(proSum.getNov().add(profit.getNov()));
				proSum.setDece(proSum.getDece().add(profit.getDece()));

			}
			return new ProfitShow(proSum.month().toString(), proSum.lastMonth().toString(), proSum.quarterly().toString(), proSum.lastQuarterly().toString());
		
		}
		
		
	}

	public void add(String id) {
		profitDao.add(id);
	}
	


}
