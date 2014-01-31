<%@page import="com.ole.model.User"%>
<%@include file="/WEB-INF/jsp/common/init.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
<jsp:include page="navigation-bar-admin.jsp"></jsp:include>


<div style="text-align: center;">
	<h3>Select Exam</h3>
	
	<%
	if(!Utility.isEmpty((String)request.getAttribute("exam-add-error")))  {%>
		<div class="errorblock" style="text-align: center;">
			Profile update failed. Cause : <%=request.getAttribute("exam-add-error") %>
		</div>
	<% } else if(!Utility.isEmpty((String)request.getAttribute("exam-add-success"))) {%>
		<div class="success-block" style="text-align: center;">
			<%=request.getAttribute("exam-add-success") %>
		</div>
	<%
	}
	%>
	
	<form action="<%=request.getContextPath()%>/admin/exam" method="post">
		<table style="margin: 0 auto;">
			<tr><td>Exam Name <font color="red">*</font></td><td><input type="text" name="examName"></td></tr>
			<tr><td>Total Questions <font color="red">*</font></td><td><input type="text" name="totalQuestion"></td></tr>
			<tr><td>Exam Time(minutes)<font color="red">*</font></td><td><input type="text" name="examTime"></td></tr>
			<tr><td>Exam Description</td><td><textarea name="examDescription" rows="5" cols="20"></textarea></td></tr>
			<tr><td/><td><input type="submit" value="Add"/><input type="reset" value="Reset"/></td></tr>
		</table>
	</form>
</div>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
</body>
</html>