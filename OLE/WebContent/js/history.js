function getHistory(resourceUrl) {
	$.ajax({
		type : "GET",
		url : resourceUrl,
		dataType: "json",
		contentType : "application/json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#exam-history-label").removeClass("errorblock");
				$("#exam-history-label").addClass("successblock");
				
				drawHistoryDetailTable(response);
				
			} else if(response != null && response.resultCode == 117) {
				$("#exam-history-label").removeClass("successblock");
				$("#exam-history-label").addClass("errorblock");
				$("#exam-history-label").html(response.resultMessage);
				drawHistoryDetailTable(null);
			} else {
				$("#exam-history-label").removeClass("successblock");
				$("#exam-history-label").addClass("errorblock");
				$("#exam-history-label").html("Unable to fetch exam history.");
				drawHistoryDetailTable(null);
			}
			
		},
		error : function(e) {
			$("#exam-history-label").removeClass("successblock");
			$("#exam-history-label").addClass("errorblock");
			$("#exam-history-label").html("Error: unable to fetch exam history.");
			drawHistoryDetailTable(null);
		}
	});
}

function drawHistoryDetailTable(data) {
	var $table = $('#user-exam-history-table');
	$table.append('<thead style="text-align : center;">').children('thead')
	.append('<tr />').children('tr').append('<th>Exam Name</th><th>Total Questions</th><th>Question Attempted</th><th>Correct Answer</th><th>Percentage</th><th>Exam Result</th>');
	var $tbody = $table.append('<tbody style="text-align : center;"/>').children('tbody');
	if (data) {
		var h = data.history;
		for ( var i = 0; i < h.length; i++) {
			$tbody.append('<tr />').children('tr:last').append(
					"<td>" + h[i].examName + "</td>").append(
					"<td>" + h[i].totalQuestion + "</td>").append(
					"<td>" + h[i].questionAttempted + "</td>").append(
					"<td>" + h[i].correctAnswer + "</td>").append(
					"<td>" + h[i].percentage + "</td>").append(
					"<td>" + h[i].examResult + "</td>");
		}
	}
	
	$('#user-exam-history-table').dataTable({
		"sDom": 'T<"clear">lfrtip',
		"oTableTools": {
			"sSwfPath": baseUrl+"/swf/copy_csv_xls_pdf.swf",
			"aButtons": ["copy","csv","xls",{"sExtends": "pdf","sPdfOrientation": "landscape","sPdfMessage": "History."},"print"]
		}
	});
}


