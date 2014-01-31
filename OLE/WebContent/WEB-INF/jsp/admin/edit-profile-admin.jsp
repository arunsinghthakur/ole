<%@page import="com.ole.model.User"%>
<%@include file="/WEB-INF/jsp/common/init.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/js/user.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
<jsp:include page="navigation-bar-admin.jsp"></jsp:include>


<div style="text-align: center;">
	<h3>Edit Profile</h3>
	
	<%
	User user = (User)request.getAttribute("user");
	%>
	<div id="profile-edit-result" style="text-align: center;"></div>
	
	<form id="profile-update-form" method="post">
		<table style="margin: 0 auto;">
			<tr><td>Username <font color="red">*</font></td><td><input value="<%=user.getUserName() %>" type="text" name="userName" class="userName" readonly="readonly"  ></td></tr>
			<tr><td>Password <font color="red">*</font></td><td><input value="<%=user.getPassword() %>" type="password" name="password" class="password"  ></td></tr>
			<tr><td>First Name <font color="red">*</font></td><td><input value="<%=user.getFirstName() %>" type="text" name="firstName" class="firstName" ></td></tr>
			<tr><td>Last Name</td><td><input value="<%=user.getLastName() %>" type="text" name="lastName" class="lastName"></td></tr>
			<tr><td>Address</td><td><input value="<%=user.getAddress() %>" type="text" name="address" class="address"></td></tr>
			<tr><td>Email Id</td><td><input value="<%=user.getEmail() %>" type="text" name="email" class="email" ></td></tr>
			<tr><td>Mobile Number</td><td><input value="<%=user.getMobile() %>" type="text" name="mobile" class="mobile" ></td></tr>
			<tr><td><input type="hidden" name="active" value="true" class="active"></td></tr>
			<tr><td/><td><input type="button" value="Update" onclick="editUser('<%=request.getContextPath()%>/admin/profile');"/>
			<%-- <input type="button" value=Delete onclick="deleteUser('<%=request.getContextPath()%>/admin/profile');"/> --%>
			</td></tr>
		</table>
	</form>
</div>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
</body>
</html>