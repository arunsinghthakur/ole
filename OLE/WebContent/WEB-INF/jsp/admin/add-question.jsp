<%@page import="com.ole.model.User"%>
<%@include file="/WEB-INF/jsp/common/init.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/js/exam.js"></script>
<script src="<%=request.getContextPath()%>/js/question.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		displayAllExam('<%=request.getContextPath()%>/admin/exam/list', 'all-exams-detail');
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
<jsp:include page="navigation-bar-admin.jsp"></jsp:include>

<div style="text-align: center;">
	<div id="all-exams-detail"></div>
	<div id="question-add-result" style="text-align: center; display: none;" ></div>
	
	<form id="question-add-form" method="post" style="display: none;">
		<table style="margin: 0 auto;">
			<tr><td>Question Description <font color="red">*</font></td><td><textarea name="questionDescription" style="width:600px; height:100px;" class="questionDescription" ></textarea></td></tr>
			<tr><td>Option1 <font color="red">*</font></td><td><textarea name="option1" style="width:600px; height:100px;" class="option1" ></textarea></td></tr>
			<tr><td>Option2 <font color="red">*</font></td><td><textarea name="option2" style="width:600px; height:100px;" class="option2" ></textarea></td></tr>
			<tr><td>Option3 <font color="red">*</font></td><td><textarea name="option3" style="width:600px; height:100px;" class="option3" ></textarea></td></tr>
			<tr><td>Option4 <font color="red">*</font></td><td><textarea name="option4" style="width:600px; height:100px;" class="option4" ></textarea></td></tr>
			<tr><td>Correct Option <font color="red">*</font></td><td>
				<select name="correctOption" class="correctOption">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
			</td></tr>
			<tr><td/><td><input type="button" value="Add" onclick="addQuestion('<%=request.getContextPath()%>/admin/question');"/><input type="reset" value="Reset"/></td></tr>
		</table>
	</form>
	
</div>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
</body>
</html>