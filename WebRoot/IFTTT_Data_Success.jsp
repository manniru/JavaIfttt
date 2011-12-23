<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="org.wzz.ifttt.response.Member.Login,org.wzz.ifttt.response.Member.ModifyData"%>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'IFTTT_Store_Data.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub"><link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub">
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub">
    <link href="./stylesheet/index.css" media="screen" rel="stylesheet" type="text/css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<%
  		ModifyData modifydata = new ModifyData();
  		modifydata.setMemberId(request.getParameter("user[login]"));
  		//modifydata.setDatatemp("wang","1990-1-1",null,null,null);	
  	 %>
  	 <center>
  	 <form action="usermain.jsp" method="post">
  	 	<%
  	 		System.out.println("Data Success AUTHCODE = " +  request.getParameter("authcode"));
  	 	 %>
  	 	<input type="text" name="authcode" value="<%=request.getParameter("authcode")%>" style="display:none"><br>
  	 	<button type="submit" class="classy primary js-oneclick" name="submit" value="Return MainUser">Return MainUser</button>
  	 </form>
  	 </center>
  </body>
</html>