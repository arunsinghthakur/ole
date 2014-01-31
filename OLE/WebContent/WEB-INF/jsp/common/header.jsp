<%@include file="init.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>@OLE</title>
<script type="text/javascript">
var examID;
var questionID;
var baseUrl = "<%=request.getContextPath()%>";
var questionList;
var examList;
var currentQIndex = 0;
var examTime;
var totalQuestion;
var timerId ;
var isSubmitRequired = true;
</script>
</head>
<body>
			<center><h1>OLE</h1></center>
	<!-- <div class="clock"><iframe
		src="http://free.timeanddate.com/clock/i3tsli3j/n1583/szw110/szh110/hoc00f/hbw0/hfc000/cf100/hgr0/facf90/mqcfff/mql6/mqw2/mqd74/mhcfff/mhl6/mhw1/mhd74/mmcf90/mml4/mmw1/mmd74/hhcfff/hmcfff"
		frameborder="0" width="112" height="112"></iframe>
    </div> -->
	<%
	if(!Utility.isEmpty((String)request.getAttribute("firstName"))) {
%>
	<div style="text-align: right;"><h6>Welcome <%=request.getAttribute("firstName") %> &nbsp; &nbsp;<a href="<%=request.getContextPath()%>/logout">Logout</a></h6></div>
<%		
	} else {
%>
	<h6 align="right"><a href="<%=request.getContextPath()%>">Home</a></h6>
<%		
	}
%>
<hr style="margin-bottom: 5px; clear: both;"/>
</body>
</html>