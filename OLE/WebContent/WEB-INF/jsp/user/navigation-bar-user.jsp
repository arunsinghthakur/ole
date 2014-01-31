<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<nav class="horizontal-nav full-width horizontalNav-notprocessed user-nav">
	<ul>
		<li><a href="<%=request.getContextPath()%>/user/exam/list/view" >Take Exam</a>
		</li>
		<li><a href="<%=request.getContextPath()%>/user/exam/history">Previous Exam(s) Result </a>
		</li>
		<li><a href="<%=request.getContextPath()%>/user/profile">Edit Profile</a>
		</li>
	</ul>
	</nav>


	<script>
$(document).ready(function() {
    $('.full-width').horizontalNav({});
});
</script>

</body>
</html>