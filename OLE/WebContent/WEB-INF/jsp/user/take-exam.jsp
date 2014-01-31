<%@page import="com.ole.model.Exam"%>
<%@page import="com.ole.model.User"%>
<%@include file="/WEB-INF/jsp/common/init.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/js/exam.js"></script>
<script src="<%=request.getContextPath()%>/js/answer.js"></script>
<script src="<%=request.getContextPath()%>/js/question.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		getUserExamList('<%=request.getContextPath()%>/user/exam/list', 'all-exams-list');
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
<jsp:include page="navigation-bar-user.jsp"></jsp:include>


<div style="text-align: center;">
	<div id="exam-detail-label"style="text-align: center; display: none; margin-top: 0px"><h3>Exam Details</h3></div>
	<div id="clock" style=" display: none;" class="clock"></div>
	<div id="all-exams-list"></div>	
	<div id="exam-result-label"style="text-align: center; display: none;"></div>
</div>


<form id="question-detail-form" method="post" style="display: none;" class="successblock">
	<table style="margin: 0 auto;">
			<tr><td></td><td style="width:600px; height:100px;" class="questionDescription"></td></tr>
			<tr><td></td><td style="width:600px; height:100px;" class="option1"></td></tr>
			<tr><td></td><td style="width:600px; height:100px;" class="option2"></td></tr>
			<tr><td></td><td style="width:600px; height:100px;" class="option3"></td></tr>
			<tr><td></td><td style="width:600px; height:100px;" class="option4"></td></tr>
			<tr><td/><td><input type="button" value="Submit Answer" onclick="submitAnswer( );"/></td></tr>
	</table>
</form>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
</body>
</html>