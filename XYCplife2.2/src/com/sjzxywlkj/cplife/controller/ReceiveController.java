package com.sjzxywlkj.cplife.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjzxywlkj.cplife.service.IReceiveService;

@Controller
@RequestMapping("/")
public class ReceiveController{
	
	@Autowired
	@Qualifier("receiveService")
	private IReceiveService receiveService;

	public void setReceiveService(IReceiveService receiveService) {
		this.receiveService = receiveService;
	}

	@RequestMapping(value="receive",method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String result = receiveService.Receive(params);
		return result;
		
		//销账
	}

}
