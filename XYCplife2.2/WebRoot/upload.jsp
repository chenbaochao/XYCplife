<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	上传账单：<br/>
   <form action="bill/AddUpload?community_id=AR3837GV84403" method="post" enctype="multipart/form-data">  
        选择文件:<input type="file" name="file" width="120px">
        <!-- <input type="text" name="community_id" value="11111"> -->
        <input type="submit" value="上传">  
    </form> <br/> 
    <hr>  <br/>
    删除账单：
<br/>
   <form action="bill/Del?community_id=AR3837GV84403" method="post" enctype="multipart/form-data">  
        选择文件:<input type="file" name="file" width="120px">
        <!-- <input type="text" name="community_id" value="11111"> -->
        <input type="submit" value="上传">  
    </form>  <br/>
    <hr>  <br/>
      线下支付账单上传：
<br/>
   <form action="bill/UpdateUpload?community_id=AR3837GV84403" method="post" enctype="multipart/form-data">  
        选择文件:<input type="file" name="file" width="120px">
        <!-- <input type="text" name="community_id" value="11111"> -->
        <input type="submit" value="上传">
    </form>  <br/>
    <hr>  <br/>
    <a href="bill/AddCaseDown?community_id=AR3837GV84403&a=546">账单上传模板</a><br/>
    <a href="bill/Down?community_id=AR3837GV84403&community_name=下花园&bill_entry_amount=200&acct_period=201803">账单下载</a>
  	<br/>
  	<a href="bill/Show?community_id=AR3837GV84403&community_name=下花园&bill_entry_amount=200&acct_period=201803&groups=a&status=待缴费">show</a>
  </body>
</html>
