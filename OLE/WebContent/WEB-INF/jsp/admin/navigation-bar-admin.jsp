<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<nav class="horizontal-nav full-width horizontalNav-notprocessed">
	<ul>
		<li><a href="<%=request.getContextPath()%>/admin/exam/view" >Add Exam</a>
		</li>
		<li><a href="<%=request.getContextPath()%>/admin/exam/eview">Edit/View Exam(s)</a>
		</li>
		<li><a href="<%=request.getContextPath()%>/admin/question/view">Add Question</a>
		</li>
		<li><a href="<%=request.getContextPath()%>/admin/question/eview">Edit/View Question(s)</a>
		</li>
		<li><a href="<%=request.getContextPath()%>/register/admin">Add Admin</a>
		</li>
		<li><a href="<%=request.getContextPath()%>/admin/profile">Edit Profile</a>
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