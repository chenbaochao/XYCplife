package com.sjzxywlkj.cplife.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sjzxywlkj.cplife.dao.SpreadMapper;
import com.sjzxywlkj.cplife.dao.UserMapper;
import com.sjzxywlkj.cplife.pojo.Spread;
import com.sjzxywlkj.cplife.pojo.User;

@Controller
@RequestMapping("/spread")
public class SpreadController {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SpreadMapper spreadMapper;

	// 添加推广用户
	@RequestMapping("/add")
	@ResponseBody
	public String add(String userId, String name, String userType, String ascription, HttpSession session) {
		User userName = (User) session.getAttribute("user");
		String type = userName.getUserType();
		if (type.equals("系统管理员") || type.equals("系统子管理员")) {
			User user = new User();
			user.setUserId(userId);
			user.setName(name);
			user.setAscription(ascription);
			user.setUserType(userType);
			userMapper.insert(user);
			if (userType.equals("推广者")) {
				Spread spread = new Spread();
				spread.setId(userId);
				spread.setAlipay(null);
				spread.setPhone(null);
				spreadMapper.insert(spread);
				return "添加成功！";
			}
		}
		return "添加失败！";
	}

	// 删除推广用户
	@RequestMapping("/delte")
	@ResponseBody
	public String delte(String userId, HttpSession session) {
		User userName = (User) session.getAttribute("user");
		String type = userName.getUserType();
		if (type.equals("系统管理员") || type.equals("系统子管理员")) {
			User user = userMapper.selectByPrimaryKey(userId);
			String userType = user.getUserType();
			if (userType.equals("推广者")) {
				spreadMapper.deleteByPrimaryKey(userId);
				return "删除成功！";
			}
		}
		return "删除失败！";
	}

	// 更改推广者电话，支付宝账号
	@RequestMapping("/updateSpreadById")
	@ResponseBody
	public String updateSpreadById(String id, String phone, String alipay, HttpSession session) {
		User userName = (User) session.getAttribute("user");
		String type = userName.getUserType();
		if (type.equals("系统管理员") || type.equals("系统子管理员")) {
			Spread spread = new Spread();
			spread.setId(id);
			spread.setPhone(phone);
			spread.setAlipay(alipay);
			spreadMapper.updateSpreadById(spread);
			return JSON.toJSONString(spread);
		}
		return JSON.toJSONString("更新失败！");
	}
}
