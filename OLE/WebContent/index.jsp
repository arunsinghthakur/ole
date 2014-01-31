
<%@include file="/WEB-INF/jsp/common/init.jsp" %>

<title>@OLE</title>
<center>
	<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
	<a href="<%=request.getContextPath()%>/login">Existing User</a>
	 &nbsp;&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/register">New User</a>
</center>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
