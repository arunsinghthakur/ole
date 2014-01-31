function submitAnswer() {
	currentQIndex = currentQIndex + 1;
	var optionSelected = $('input:radio[name=correctOption]:checked').val();
	if(optionSelected > 0 && optionSelected < 5) {
		saveAnswer(optionSelected);
	}
	if(currentQIndex == totalQuestion) {
		currentQIndex = 0;
		removeClock();
		calculateResult();
	} else {
		displayExamQuestion();
	}
}

function saveAnswer(optionSelected) {
	$.ajax({
		type : "POST",
		url : baseUrl + "/user/0/exam/"+examID+"/question/"+questionID+"/optionSelect/"+optionSelected,
		dataType: "json",
		contentType : "application/json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				//alert(response.resultMessage);
			} else if(response != null && response.resultCode == 113) { 
				alert(response.resultMessage);
			} else {
				alert("Unable to submit answer.");
			}
		},
		error : function(e) {
			alert("Sorry unable to submit answer.");
		}
	});
}

function calculateResult() {
	$("#question-detail-form").remove();
	$("#clock").remove();
	$("#all-exams-list").remove();
	$("#exam-detail-label").remove();
	$("#exam-result-label").show();
	displayResult();
}
function displayResult() {
	$(".user-nav").show();
	isSubmitRequired = false;
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/user/0/exam/"+examID+"/result",
		dataType: "json",
		contentType : "application/json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#exam-result-label").removeClass("errorblock");
				$("#exam-result-label").addClass("successblock");
				
				var $table = $('<table style="margin: 0 auto; width:100%">');
				$table.append('<caption><h3> Result </h3></caption>')
				.append('<thead style="text-align : center;">').children('thead')
				.append('<tr />').children('tr').append('<th>Exam Name</th><th>Total Questions</th><th>Question Attempted</th><th>Correct Answer</th><th>Percentage</th><th>Exam Result</th>');
				var $tbody = $table.append('<tbody style="text-align : center;"/>').children('tbody');
				$tbody.append('<tr />').children('tr:last')
				.append("<td>"+response.result.exam.examName+"</td>")
				.append("<td>"+response.result.exam.totalQuestion+"</td>")
				.append("<td>"+response.result.questionAttempted+"</td>")
				.append("<td>"+response.result.correctAnswer+"</td>")
				.append("<td>"+response.result.percentage+"</td>")
				.append("<td>"+response.result.examResult+"</td>");
				
				$table.appendTo("#exam-result-label");
				
			} else if(response != null && response.resultCode == 115) {
				$("#exam-result-label").removeClass("successblock");
				$("#exam-result-label").addClass("errorblock");
				$("#exam-result-label").html(response.resultMessage);
			} else {
				$("#exam-result-label").removeClass("successblock");
				$("#exam-result-label").addClass("errorblock");
				$("#exam-result-label").html("Unable to display exam result.");
			}
			
		},
		error : function(e) {
			$("#exam-result-label").removeClass("successblock");
			$("#exam-result-label").addClass("errorblock");
			$("#exam-result-label").html("Error: Unable to display exam result.");
		}
	});
}