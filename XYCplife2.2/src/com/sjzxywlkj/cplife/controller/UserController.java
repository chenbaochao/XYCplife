package com.sjzxywlkj.cplife.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.sjzxywlkj.cplife.alipay.result.LoginAlipay;
import com.sjzxywlkj.cplife.dao.AdminNumMapper;
import com.sjzxywlkj.cplife.dao.UserMapper;
import com.sjzxywlkj.cplife.pojo.AdminNum;
import com.sjzxywlkj.cplife.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private AdminNumMapper adminNumMapper;

	@RequestMapping("/exit")
	@ResponseBody
	public String exit(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.removeAttribute("user");
		Cookie cookie = new Cookie("user", null);
		cookie.setMaxAge(15 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "success";
	}

	// 添加用户
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(User record) {
		Integer count = userMapper.count(record);
		switch (record.getUserType()) {
		// 系统总管理员
		case "系统总管理员":
			if (count >= 1) {
				return "超出最大数值";
			} else {
				userMapper.insert(record);
			}
			break;
		// 系统子管理员
		case "系统子管理员":
			if (count >= 2) {
				return "超出最大数值";
			} else {
				userMapper.insert(record);
			}
			break;
		// 物业公司管理员
		case "物业公司管理员":
			if (count >= 1) {
				return "超出最大数值";
			} else {
				userMapper.insert(record);
			}
			break;
		// 物业子公司管理员
		case "物业子公司管理员":
			if (count >= 2) {
				return "超出最大数值";
			} else {
				userMapper.insert(record);
			}
			break;
		// 小区管理员
		case "小区管理员":
			if (count >= 1) {
				return "超出最大数值";
			} else {
				userMapper.insert(record);
			}
			break;
		// 小区子管理员
		case "小区子管理员":
			if (count >= 2) {
				return "超出最大数值";
			} else {
				userMapper.insert(record);
			}
			break;
		case "推广":
			if (count >= 2) {
				return "超出最大数值";
			} else {
				userMapper.insert(record);
			}
			break;
		default:
			return "不合理的角色";
		}
		return "success";
	}

	// 进行操作
	@RequestMapping("/update")
	@ResponseBody
	public String doupdate(User record, Model model) {
		User olduser = userMapper.selectByPrimaryKey(record.getUserId());
		if (record.getUserId().equals(olduser.getUserId()) && record.getName().equals(olduser.getName())
				&& record.getUserType().equals(olduser.getUserType())
				&& record.getAscription().equals(olduser.getAscription())) {

			return "信息未修改";
		}
		userMapper.updateByPrimaryKey(record);
		return "修改成功";
	}

	@RequestMapping("selectByType")
	@ResponseBody
	public List<User> selectByType(String type) {
		return this.userMapper.selectByType(type);
	}

	// 系统子管理员
	@RequestMapping("/addSystemSon")
	@ResponseBody
	public String addSystemSon(String userId, String name, String userType, String ascription, HttpSession session) {
		User userName = (User) session.getAttribute("user");
		String type = userName.getUserType();
		User user = new User();
		if (type.equals("系统总管理员")) {
			user.setUserId(userId);
			user.setUserType(userType);
			user.setName(name);
			user.setAscription(ascription);
			userMapper.insert(user);
			return JSON.toJSONString(user);
		}
		return null;
	}

	// 添加物业管理员
	@RequestMapping("/addProperty")
	@ResponseBody
	public String addProperty(String userId, String name, String userType, String ascription, HttpSession session) {
		User userName = (User) session.getAttribute("user");
		String type = userName.getUserType();
		User user = new User();
		if (type.equals("系统总管理员") || type.equals("系统子管理员")) {
			int num = userMapper.selectUserTypeNum();
			if (num < 1) {
				user.setUserId(userId);
				user.setUserType(userType);
				user.setName(name);
				user.setAscription(ascription);
				userMapper.insert(user);
				return JSON.toJSONString(user);
			}
		}
		return JSON.toJSONString("添加物业管理员失败！");
	}

	// 添加小区管理员
	@RequestMapping("/addCommunity")
	@ResponseBody
	public String addCommunity(String userId, String name, String userType, String ascription, HttpSession session) {
		User userName = (User) session.getAttribute("user");
		String type = userName.getUserType();
		User user = new User();
		if (type.equals("系统总管理员") || type.equals("系统子管理员") || type.equals("物业公司管理员") || type.equals("物业公司子管理员")) {
			int num = userMapper.selectUserTypeNum1();
			if (num < 1 && num != 1) {
				user.setUserId(userId);
				user.setUserType(userType);
				user.setName(name);
				user.setAscription(ascription);
				userMapper.insert(user);
				return JSON.toJSONString(user);
			}
		}
		return JSON.toJSONString("添加小区管理员失败！");
	}

	// 添加物业子管理员
	@RequestMapping("/addPropertySon")
	@ResponseBody
	public String addPropertySon(String Id, String userId, String name, String userType, String ascription,
			HttpSession session) {
		AdminNum adminNum = new AdminNum();
		adminNum.setId(Id);
		Integer num = adminNumMapper.count(adminNum);
		System.out.println(num);
		if (num < 2 && num != 2) {
			User userName = (User) session.getAttribute("user");
			String type = userName.getUserType();
			if (type.equals("系统总管理员") || type.equals("系统子管理员") || type.equals("物业公司管理员")) {
				User user = new User();
				user.setUserId(userId);
				user.setName(name);
				user.setUserType(userType);
				user.setAscription(ascription);
				int ins = userMapper.insert(user);
				System.out.println();
				if (ins > 0) {
					++num;
					adminNum.setName(name);
					adminNum.setNum(num);
					adminNumMapper.updateByPrimaryKey(adminNum);
					return JSON.toJSONString(num);
				} else {
					return null;
				}
			}
		}
		return JSON.toJSONString("您缺少权限！添加物业子管理员失败！");
	}

	// 添加小区子管理员
	@RequestMapping("/addCommunitySon")
	@ResponseBody
	public String addCommunitySon(String Id, String userId, String name, String userType, String ascription,
			HttpSession session) {
		AdminNum adminNum = new AdminNum();
		adminNum.setId(Id);
		Integer num = adminNumMapper.count(adminNum);
		System.out.println(num);
		if (num < 2 && num != 2) {
			User userName = (User) session.getAttribute("user");
			String type = userName.getUserType();
			if (type.equals("系统总管理员") || type.equals("系统子管理员") || type.equals("物业公司管理员") || type.equals("物业公司子管理员")
					|| type.equals("小区管理员")) {
				User user = new User();
				user.setUserId(userId);
				user.setName(name);
				user.setUserType(userType);
				user.setAscription(ascription);
				int ins = userMapper.insert(user);
				if (ins > 0) {
					++num;
					adminNum.setName(name);
					adminNum.setNum(num);
					adminNumMapper.updateByPrimaryKey(adminNum);
					return JSON.toJSONString(num);
				} else {
					return null;
				}
			}
		}
		return JSON.toJSONString("您缺少权限！添加小区子管理员失败！");
	}

	// 更改用户名
	@RequestMapping("/updateNameById")
	@ResponseBody
	public String updateNameById(String userId, String name) {
		User user = new User();
		user.setUserId(userId);
		user.setName(name);
		int flag = userMapper.updateNameById(user);
		if (flag > 0) {
			return JSON.toJSONString("更改用户名称成功！");
		}
		return JSON.toJSONString("更改用户名称失败！");

	}

	// 按类别查询用户
	@RequestMapping("/selectUserByType")
	@ResponseBody
	public String selectUserByType(String userType) {
		User user = new User();
		user.setUserType(userType);
		List<User> userList = userMapper.selectUserByType(user);
		if (userList != null) {
			return JSON.toJSONString(userList);
		} else {
			return "您查询的用户类型不存在，请重新进行查询！";
		}
	}

	// 删除用户
	@RequestMapping("/delteUser")
	@ResponseBody
	public String delteUser(String userId) {
		User user = new User();
		user.setUserId(userId);

		String userType = userMapper.selectUserTypeById(user);
		System.out.println(userType);
		if (userType.equals("推广")) {
			adminNumMapper.deleteByPrimaryKey(userId);
		}

		int flag = userMapper.deleteByPrimaryKey(userId);
		if (flag > 0) {
			return JSON.toJSONString("删除用户成功！");
		}
		return JSON.toJSONString("删除用户失败！");
	}

	/**
	 * 
	 * @param auth_code
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws AlipayApiException
	 * @throws UnsupportedEncodingException
	 */

	// 用户登录
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public String login(String user_id, HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws AlipayApiException, UnsupportedEncodingException {
		// 真实环境下调用
		// String user_id = LoginAlipay.login(auth_code);

		User user = userMapper.selectByPrimaryKey(user_id);

		Cookie cookie = null;
		String url = "redirect:../../xywy/error.html";
		if (user == null) {

			cookie = new Cookie("user_id", URLEncoder.encode(user_id, "UTF-8"));

		} else {

			switch (user.getUserType()) {
			case "系统总管理员":
				url = "redirect:user.jsp";
				break;
			case "系统子管理员":
				url = "redirect:user.jsp";
				break;
			case "物业公司管理员":
				url = "redirect:user.jsp";
				break;
			case "物业公司子管理员":
				url = "redirect:user.jsp";
				break;
			case "小区管理员":
				url = "redirect:user.jsp";
				break;
			case "小区子管理员":
				url = "redirect:user.jsp";
				break;
			case "推广":
				url = "redirect:user.jsp";
				break;
			default:
				break;
			}
			session.setAttribute("user", user);

			cookie = new Cookie("user", URLEncoder.encode(JSON.toJSONString(user), "UTF-8"));

		}
		cookie.setMaxAge(15 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);
		return url;
	}

	// 删除用户
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteByPrimaryKey(String userId) {
		userMapper.deleteByPrimaryKey(userId);
		return "success";

	}

}
