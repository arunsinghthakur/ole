function displayQuestionForm(examid) {
	$("#question-add-result").show();
	$("#question-add-form").show();
	
	setExamId(examid);
	$('#all-exams-detail').empty();
	displayExamDetail( );
}


function addQuestion(resourceUrl) {
	var q = new Object();
	q.questionDescription = $("#question-add-form .questionDescription").val();
	q.option1 = $("#question-add-form .option1").val();
	q.option2 = $("#question-add-form .option2").val();
	q.option3 = $("#question-add-form .option3").val();
	q.option4 = $("#question-add-form .option4").val();
	q.correctOption = $("#question-add-form .correctOption").val();
	
	$.ajax({
		type : "POST",
		url : baseUrl+"/admin/exam/"+examID+"/question",
		dataType: "json",
		contentType : "application/json",
		data : JSON.stringify(q),
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				displayExamDetail( );
				$("#question-add-result").removeClass("errorblock");
				$("#question-add-result").addClass("successblock");
				$("#question-add-result").html(response.resultMessage);
			} else if(response != null && response.resultCode == 106) {
				$("#question-add-result").removeClass("successblock");
				$("#question-add-result").addClass("errorblock");
				$("#question-add-result").html(response.resultMessage);
			} else {
				$("#question-add-result").removeClass("successblock");
				$("#question-add-result").addClass("errorblock");
				$("#question-add-result").html("Unable to add question.");
			}
			
		},
		error : function(e) {
			$("#question-add-result").removeClass("successblock");
			$("#question-add-result").addClass("errorblock");
			$("#question-add-result").html("Error: unable to add question.");
		}
	});
}

function displayExamDetail( ) {
	$('#all-exams-detail').empty();
	
	$.ajax({
		type : "GET",
		url : baseUrl + "/admin/exam?examId=" + examID ,
		contentType : "application/json",
		dataType: "json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				var $table = $('<table style="margin: 0 auto; width:100%">');
				$table.append('<caption><h3>Exam Details</h3></caption>')
				.append('<thead style="text-align : center;">').children('thead')
				.append('<tr />').children('tr').append('<th>Exam Name</th><th>Total Questions</th><th>Exam Time(min.)</th><th>Question Added</th><th>Exam Status</th><th>Exam Description</th><th>Pass Percentage</th>');
				var $tbody = $table.append('<tbody style="text-align : center;"/>').children('tbody');

				var exam = response.exam;
				
				$tbody.append('<tr />').children('tr:last')
				.append("<td>"+exam.examName+"</td>")
				.append("<td>"+exam.totalQuestion+"</td>")
				.append("<td>"+exam.examTime+"</td>")
				.append("<td>"+exam.question.length+"</td>")
				.append("<td>"+exam.examStatus+"</td>")
				.append("<td>"+exam.examDescription+"</td>")
				.append("<td>"+exam.passPercentage+"</td>");
				$table.appendTo('#all-exams-detail');
			} 
		}
	});
}

function displayAllQuestionDetails(examId) {
	$("#all-exams-detail-question-edit").hide();
	$("#all-question-detail-edit").show();
	setExamId(examId);
	getAllQuestionsDetail(baseUrl+"/admin/exam/"+examId+"/question/list", 'all-question-detail-edit');
}

function getAllQuestionsDetail(resourceUrl, div) {
	$.ajax({
		type : "GET",
		url : resourceUrl,
		dataType: "json",
		contentType : "application/json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#"+div).removeClass("errorblock");
				$("#"+div).addClass("successblock");
				
				drawQuestionDetailTable(response, div);
				
			} else if(response != null && response.resultCode == 108) {
				$("#"+div).removeClass("successblock");
				$("#"+div).addClass("errorblock");
				$("#"+div).html(response.resultMessage);
			} else {
				$("#"+div).removeClass("successblock");
				$("#"+div).addClass("errorblock");
				$("#"+div).html("Unable to fetch question list.");
			}
			
		},
		error : function(e) {
			$("#"+div).removeClass("successblock");
			$("#"+div).addClass("errorblock");
			$("#"+div).html("Error: unable to fetch question list.");
		}
	});
}

function drawQuestionDetailTable(data, div) {

	var $table = $('<table style="margin: 0 auto; width:100%">');
	$table.append('<caption><h3>'+data.resultMessage+'</h3></caption>')
	.append('<thead style="text-align : center;">').children('thead')
	.append('<tr />').children('tr').append('<th>Select</th><th>Question Description</th><th>Option1</th><th>Option2</th><th>Option3</th><th>Option4</th><th>Correct Option</th>');
	var $tbody = $table.append('<tbody style="text-align : center;"/>').children('tbody');

	var questionList = data.questions;
	
	setQuestionList(questionList);
	for(var i =  0; i < questionList.length; i++) {
		
		$tbody.append('<tr />').children('tr:last')
		.append("<td> <input type='radio' name='question-details' onchange=displayEditQuestionForm("+i+"); ></input></td>")
		.append("<td>"+questionList[i].questionDescription+"</td>")
		.append("<td>"+questionList[i].option1+"</td>")
		.append("<td>"+questionList[i].option2+"</td>")
		.append("<td>"+questionList[i].option3+"</td>")
		.append("<td>"+questionList[i].option4+"</td>")
		.append("<td>"+questionList[i].correctOption+"</td>");
	}

	$table.appendTo('#'+div);
}

function setQuestionList(qList) {
	questionList = qList;
}

function displayEditQuestionForm(index) {
	$("#all-question-detail-edit").hide();
	$("#question-edit-result").show();
	$("#question-edit-label").show();
	$("#question-edit-form").show();
	
	var question = questionList[index];
	
	setQuestionId(question.questionId);
	
	$("#question-edit-form .questionDescription").val(question.questionDescription);
	$("#question-edit-form .option1").val(question.option1);
	$("#question-edit-form .option2").val(question.option2);
	$("#question-edit-form .option3").val(question.option3);
	$("#question-edit-form .option4").val(question.option4);
	$("#question-edit-form .correctOption").val(question.correctOption);
}

function setQuestionId(questionId) {
	questionID = questionId;
}

function updateQuestion(resourceUrl) {

	var q = new Object();
	q.questionId = questionID;
	q.questionDescription = $("#question-edit-form .questionDescription").val();
	q.option1 = $("#question-edit-form .option1").val();
	q.option2 = $("#question-edit-form .option2").val();
	q.option3 = $("#question-edit-form .option3").val();
	q.option4 = $("#question-edit-form .option4").val();
	q.correctOption = $("#question-edit-form .correctOption").val();
	
	$.ajax({
		type : "PUT",
		url : baseUrl+"/admin/exam/"+examID+"/question/"+questionID,
		dataType: "json",
		contentType : "application/json",
		data : JSON.stringify(q),
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#question-edit-result").removeClass("errorblock");
				$("#question-edit-result").addClass("successblock");
				$("#question-edit-result").html(response.resultMessage);
			} else if(response != null && response.resultCode == 109) {
				$("#question-edit-result").removeClass("successblock");
				$("#question-edit-result").addClass("errorblock");
				$("#question-edit-result").html(response.resultMessage);
			} else {
				$("#question-edit-result").removeClass("successblock");
				$("#question-edit-result").addClass("errorblock");
				$("#question-edit-result").html("Unable to update question.");
			}
			
		},
		error : function(e) {
			$("#question-edit-result").removeClass("successblock");
			$("#question-edit-result").addClass("errorblock");
			$("#question-edit-result").html("Error: unable to update question.");
		}
	});

}


function deleteQuestion(resourceUrl) {

	$.ajax({
		type : "DELETE",
		url : baseUrl+"/admin/exam/"+examID+"/question/"+questionID,
		dataType: "json",
		contentType : "application/json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#question-edit-result").removeClass("errorblock");
				$("#question-edit-result").addClass("successblock");
				$("#question-edit-result").html(response.resultMessage);
			} else if(response != null && response.resultCode == 111) {
				$("#question-edit-result").removeClass("successblock");
				$("#question-edit-result").addClass("errorblock");
				$("#question-edit-result").html(response.resultMessage);
			} else {
				$("#question-edit-result").removeClass("successblock");
				$("#question-edit-result").addClass("errorblock");
				$("#question-edit-result").html("Unable to delete question.");
			}
			
		},
		error : function(e) {
			$("#question-edit-result").removeClass("successblock");
			$("#question-edit-result").addClass("errorblock");
			$("#question-edit-result").html("Error: unable to delete question.");
		}
	});

}


