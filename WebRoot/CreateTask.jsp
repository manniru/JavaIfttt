<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'CreateTask.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="./stylesheet/index.css" media="screen" rel="stylesheet" type="text/css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@page import="org.wzz.ifttt.response.Member.Task"%>

  </head>
  	<%
  		Task task = new Task();
  		String thisDescription;
  		String thatDescription;
  		if(request.getParameter("this[type]").equals("time-after"))	{
  			thisDescription = request.getParameter("time_event[date]");
  		}
  		else if(request.getParameter("this[type]").equals("weibo-update"))	{
  			thisDescription = request.getParameter("weibo_event[name]") + "#&#" + request.getParameter("weibo_event[keyword]");
  		}
  		else thisDescription = request.getParameter("gmail_event[name]") + "#&#" + request.getParameter("gmail_event[password]");
  		
  		if(request.getParameter("that[type]").equals("weibo-update")) {
  			thatDescription = request.getParameter("weibo_action[name]") + "#&#" + request.getParameter("weibo_action[keyword]") + "#&#" + request.getParameter("weibo_action[data]");
  		}
  		else thatDescription = request.getParameter("gmail_action[name]") + "#&#" + request.getParameter("gmail_action[password]") + "#&#" + request.getParameter("gmail_action[data]");
  		task.createTask(Long.parseLong(request.getParameter("authcode")),request.getParameter("task[name]"),request.getParameter("task[description]"),thisDescription,request.getParameter("this[type]"),thatDescription,request.getParameter("that[type]"),false);
  	%>
  <body>
    <center>
  	 <form action="usermain.jsp" method="post">
  	 	<input type="text" name="authcode" value="<%=request.getParameter("authcode")%>" style="display:none"><br>
  	 	<button type="submit" class="classy primary js-oneclick" name="submit" value="Return MainUser">Return MainUser</button>
  	 </form>
  	 </center>
  </body>
</html>
