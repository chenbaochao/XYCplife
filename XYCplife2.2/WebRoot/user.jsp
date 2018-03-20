<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
    <script type="text/javascript">
    </script>
  </head>
  
  <body>
  
  
  
	<!--  删除用户 -->
	//删除用户 
    <form action="${pageContext.request.contextPath }/user/delteUser" method="POST">
    	userId：<input type="text" name="userId" value="3"/><br>
    	<input type="submit" value="删除"/>
    </form>
    
    
    
    <!--  根据类别查询用户 -->
    //根据类别查询用户 
    <form action="${pageContext.request.contextPath }/user/selectUserByType" method="POST">
    	userType：<input type="text" name="userType" value="系统子管理员"/><br>
    	<input type="submit" value="查询"/>
    </form>
    
 	// 更改用户名
     <form action="${pageContext.request.contextPath }/user/updateNameById" method="POST">
    	userId：<input type="text" name="userId" value="1"/><br>
    	name：<input type="text" name="name" value="admin"/><br>
    	<input type="submit" value="修改"/>
    </form>
    // 添加小区子管理员
     <form action="${pageContext.request.contextPath }/user/addCommunitySon" method="POST">
      	Id：<input type="text" name="Id" value="7"/><br>
    	userId：<input type="text" name="userId" value="8"/><br>
    	name：<input type="text" name="name" value="admin"/><br>
    	userType：<input type="text" name="userType" value="小区子管理员"/><br>
    	ascription：<input type="text" name="ascription" value="8"/><br>
    	<input type="submit" value="添加"/>
    </form>
    // 添加物业子管理员
	  <form action="${pageContext.request.contextPath }/user/addPropertySon" method="POST">
	  	Id：<input type="text" name="Id" value="5"/><br>
    	userId：<input type="text" name="userId" value="8"/><br>
    	name：<input type="text" name="name" value="admin"/><br>
    	userType：<input type="text" name="userType" value="物业子管理员"/><br>
    	ascription：<input type="text" name="ascription" value="8"/><br>
    	<input type="submit" value="添加"/>
    </form>
    
    // 添加小区管理员
	<form action="${pageContext.request.contextPath }/user/addCommunity" method="POST">
    	userId：<input type="text" name="userId" value="8"/><br>
    	name：<input type="text" name="name" value="admin"/><br>
    	userType：<input type="text" name="userType" value="小区管理员"/><br>
    	ascription：<input type="text" name="ascription" value="8"/><br>
    	<input type="submit" value="添加"/>
    </form>
    // 添加物业管理员
	<form action="${pageContext.request.contextPath }/user/addProperty" method="POST">
    	userId：<input type="text" name="userId" value="8"/><br>
    	name：<input type="text" name="name" value="admin"/><br>
    	userType：<input type="text" name="userType" value="物业管理员"/><br>
    	ascription：<input type="text" name="ascription" value="8"/><br>
    	<input type="submit" value="添加"/>
    </form>
    	// 系统子管理员
	<form action="${pageContext.request.contextPath }/user/addSystemSon" method="POST">
    	userId：<input type="text" name="userId" value="8"/><br>
    	name：<input type="text" name="name" value="admin"/><br>
    	userType：<input type="text" name="userType" value="物业管理员"/><br>
    	ascription：<input type="text" name="ascription" value="8"/><br>
    	<input type="submit" value="添加"/>
    </form>
    
    	// 更新子管理员的数量
	<form action="${pageContext.request.contextPath }/adminName/updateNum" method="get">
    	Id：<input type="text" name="Id" value="7"/><br>
    	name：<input type="text" name="name" value="admin"/><br>
    	num：<input type="text" name="num" value="3"/><br>
    	<input type="submit" value="更新"/>
    </form>
    	// 添加推广用户
	<form action="${pageContext.request.contextPath }/spread/add" method="get">
    	userId：<input type="text" name="userId" value="8"/><br>
    	name：<input type="text" name="name" value="admin"/><br>
    	userType：<input type="text" name="userType" value="推广者"/><br>
    	ascription:<input type="text" name="ascription" value="9"/><br>
    	<input type="submit" value="更新"/>
    </form>
    // 删除推广用户
	<form action="${pageContext.request.contextPath }/spread/delte" method="get">
    	userId：<input type="text" name="userId" value="8"/><br>
    	<input type="submit" value="删除"/>
    </form>
    
    // 更改推广者电话，支付宝账号
		<form action="${pageContext.request.contextPath }/spread/updateSpreadById" method="get">
    	id：<input type="text" name="id" value="8"/><br>
    	phone：<input type="text" name="phone" value="8"/><br>
    	alipay：<input type="text" name="alipay" value="8"/><br>
    	<input type="submit" value="更改"/>
    </form>
    
    
  </body>
</html>
