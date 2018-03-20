package com.sjzxywlkj.cplife.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sjzxywlkj.cplife.dao.AdminNumMapper;
import com.sjzxywlkj.cplife.dao.UserMapper;
import com.sjzxywlkj.cplife.pojo.AdminNum;
import com.sjzxywlkj.cplife.pojo.User;

@Controller
@RequestMapping("/adminName")
public class adminNumController {
	@Autowired
	private AdminNumMapper adminNumMapper;
	@Autowired
	private UserMapper userMapper;

	// 更新子管理员的数量
	@RequestMapping("/updateNum")
	@ResponseBody
	public String updateNum(String Id, String name, int num,HttpSession session) {
		User userName = (User) session.getAttribute("user");
		String type = userName.getUserType();
		System.out.println(type);
		if (type.equals("系统总管理员") || type.equals("系统子管理员")) {
			AdminNum adminNum = new AdminNum();
			adminNum.setId(Id);
			adminNum.setName(name);
			adminNum.setNum(num);
			int flag = adminNumMapper.updateByPrimaryKey(adminNum);
			return JSON.toJSONString(flag);
		}
		return JSON.toJSONString("更新子管理员失败！");
	}

}
