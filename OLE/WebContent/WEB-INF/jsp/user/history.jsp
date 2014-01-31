<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
<jsp:include page="navigation-bar-user.jsp"></jsp:include>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/TableTools.js"></script>
<script src="<%=request.getContextPath()%>/js/ZeroClipboard.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datatable_page.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/data_table.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/TableTools.css" type="text/css"></link>
<script src="<%=request.getContextPath()%>/js/history.js"></script>
</head>
<body>

<table  class="display" id="user-exam-history-table">
</table>

<script type="text/javascript">
	$(document).ready(function() {
		getHistory('<%=request.getContextPath()%>/user/0/exam/history');
	});
</script>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
</body>
</html>