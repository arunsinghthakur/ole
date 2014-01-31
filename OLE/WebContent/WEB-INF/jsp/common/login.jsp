<%@include file="init.jsp" %>
<html>
<head>
</head>
<jsp:include page="header.jsp"></jsp:include>

<body onload='document.f.j_username.focus();' style="text-align: center;">
	<h3>Login</h3>

	<c:if test="${not empty error}">
		<div class="errorblock" style="text-align: center;">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	
	<%if(!Utility.isEmpty((String)request.getAttribute("reg-successful")))  {%>
		<div class="success-block" style="text-align: center;">
			<%=request.getAttribute("reg-successful") %>
		</div>
	<% } %>
 
	<form  name='f' action="<c:url value='j_spring_security_check' />"
		method='POST' >
 
		<table style="margin: 0 auto;">
			<tr>
				<td>Username:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding-left:100px ; padding-top:20px"><input name="submit" type="submit"
					value="SignIn" /> 
					<input type="reset" value="Reset"/>
				</td>
			</tr>
		</table>
 
	</form>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>