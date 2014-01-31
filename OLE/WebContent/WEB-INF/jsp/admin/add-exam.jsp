<%@page import="com.ole.model.User"%>
<%@include file="/WEB-INF/jsp/common/init.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/js/exam.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
<jsp:include page="navigation-bar-admin.jsp"></jsp:include>


<div style="text-align: center;">
	<h3>Add Exam</h3>
	
	<div id="exam-add-result" style="text-align: center;"></div>
	
	
	<form id="exam-add-form" method="post">
		<table style="margin: 0 auto;">
			<tr><td>Exam Name <font color="red">*</font></td><td><input type="text" name="examName" class="examName" ></td></tr>
			<tr><td>Total Questions <font color="red">*</font></td><td><input type="text" name="totalQuestion" class="totalQuestion"></td></tr>
			<tr><td>Answer Time per Question(minutes)<font color="red">*</font></td><td><input type="text" name="examTime" class="examTime" ></td></tr>
			<tr><td>Exam Qualifying Percentage <font color="red">*</font></td><td><input type="text" name="passPercentage" class="passPercentage"></td></tr>
			<tr><td>Exam Description</td><td><textarea name="examDescription" rows="5" cols="20" class="examDescription"></textarea></td></tr>
			<tr><td/><td><input type="button" value="Add" onclick="addExam('<%=request.getContextPath()%>/admin/exam');"/><input type="reset" value="Reset"/></td></tr>
		</table>
	</form>
</div>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
</body>
</html>