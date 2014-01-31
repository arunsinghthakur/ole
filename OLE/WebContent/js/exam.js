function addExam(resourceUrl) {
	var e = new Object();
	e.examName = $("#exam-add-form .examName").val();
	e.totalQuestion = $("#exam-add-form .totalQuestion").val();
	e.examTime = $("#exam-add-form .examTime").val();
	e.examDescription = $("#exam-add-form .examDescription").val();
	e.passPercentage = $("#exam-add-form .passPercentage").val();
	
	$.ajax({
		type : "POST",
		url : resourceUrl,
		dataType: "json",
		data : JSON.stringify(e),
		contentType : "application/json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#exam-add-result").removeClass("errorblock");
				$("#exam-add-result").addClass("successblock");
				$("#exam-add-result").html(response.resultMessage);
			} else if(response != null && response.resultCode == 102) {
				$("#exam-add-result").removeClass("successblock");
				$("#exam-add-result").addClass("errorblock");
				$("#exam-add-result").html(response.resultMessage);
			} else {
				$("#exam-add-result").removeClass("successblock");
				$("#exam-add-result").addClass("errorblock");
				$("#exam-add-result").html("Unable to add exam.");
			}
			
		},
		error : function(e) {
			$("#exam-add-result").removeClass("successblock");
			$("#exam-add-result").addClass("errorblock");
			$("#exam-add-result").html("Error: unable to add exam.");
		}
	});
}


function displayAllExam(resourceUrl, div) {
	$.ajax({
		type : "GET",
		url : resourceUrl,
		dataType: "json",
		contentType : "application/json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#"+div).removeClass("errorblock");
				$("#"+div).addClass("successblock");
				
				drawExamDetailTable(response, div);
				
			} else if(response != null && response.resultCode == 103) {
				$("#"+div).removeClass("successblock");
				$("#"+div).addClass("errorblock");
				$("#"+div).html(response.resultMessage);
			} else {
				$("#"+div).removeClass("successblock");
				$("#"+div).addClass("errorblock");
				$("#"+div).html("Unable to fetch exam list.");
			}
			
		},
		error : function(e) {
			$("#"+div).removeClass("successblock");
			$("#"+div).addClass("errorblock");
			$("#"+div).html("Error: unable to fetch exam list.");
		}
	});
}

function drawExamDetailTable(data, div) {
	var $table = $('<table style="margin: 0 auto; width:100%">');
	$table.append('<caption><h3>'+data.resultMessage+'</h3></caption>')
	.append('<thead style="text-align : center;">').children('thead')
	.append('<tr />').children('tr').append('<th>Select</th><th>Exam Name</th><th>Total Questions</th><th>Exam Time(min.)</th><th>Question Added</th><th>Pass Percentage</th><th>Exam Status</th>');
	var $tbody = $table.append('<tbody style="text-align : center;"/>').children('tbody');

	var examList = data.exams;
	
	var methodName;
	if(div == 'all-exams-detail'){
		methodName = 'displayQuestionForm';
	} else if(div == 'all-exams-detail-question-edit'){
		methodName = 'displayAllQuestionDetails';
	} else {
		methodName = 'displayEditExamForm';
	}
	for(var i =  0; i < examList.length; i++) {
		
		
		$tbody.append('<tr />').children('tr:last')
		
		.append("<td> <input type='radio' name='exam-id' value="+examList[i].examId+" onchange="+methodName+"("+examList[i].examId+"); ></input></td>")
		.append("<td>"+examList[i].examName+"</td>")
		.append("<td>"+examList[i].totalQuestion+"</td>")
		.append("<td>"+examList[i].examTime+"</td>")
		.append("<td>"+examList[i].question.length+"</td>")
		.append("<td>"+examList[i].passPercentage+"</td>")
		.append("<td>"+examList[i].examStatus+"</td>");
	}

	$table.appendTo('#'+div);

}

function displayEditExamForm(examid) {
	$("#exam-edit-result").show();
	$("#exam-edit-form").show();
	$("#exam-edit-label").show();
	
	setExamId(examid);
	$('#all-exams-detail-edit').empty();
	$('#all-exams-detail-edit').hide();
	
	$.ajax({
		type : "GET",
		url : baseUrl + "/admin/exam?examId=" + examID ,
		contentType : "application/json",
		dataType: "json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				var exam = response.exam;
				$("#exam-edit-form .examName").val(exam.examName);
				$("#exam-edit-form .totalQuestion").val(exam.totalQuestion);
				$("#exam-edit-form .examTime").val(exam.examTime);
				$("#exam-edit-form .examDescription").val(exam.examDescription);
				$("#exam-edit-form .passPercentage").val(exam.passPercentage);
			} 
		}
	});

}

function editExam(resourceUrl) {

	var e = new Object();
	e.examId = examID;
	e.examName = $("#exam-edit-form .examName").val();
	e.totalQuestion = $("#exam-edit-form .totalQuestion").val();
	e.examTime = $("#exam-edit-form .examTime").val();
	e.examDescription = $("#exam-edit-form .examDescription").val();
	e.passPercentage = $("#exam-edit-form .passPercentage").val();
	
	$.ajax({
		type : "PUT",
		url : resourceUrl+"/"+examID,
		dataType: "json",
		data : JSON.stringify(e),
		contentType : "application/json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#exam-edit-result").removeClass("errorblock");
				$("#exam-edit-result").addClass("successblock");
				$("#exam-edit-result").html(response.resultMessage);
			} else if(response != null && response.resultCode == 107) {
				$("#exam-edit-result").removeClass("successblock");
				$("#exam-edit-result").addClass("errorblock");
				$("#exam-edit-result").html(response.resultMessage);
			} else {
				$("#exam-edit-result").removeClass("successblock");
				$("#exam-edit-result").addClass("errorblock");
				$("#exam-edit-result").html("Unable to update exam.");
			}
			
		},
		error : function(e) {
			$("#exam-edit-result").removeClass("successblock");
			$("#exam-edit-result").addClass("errorblock");
			$("#exam-edit-result").html("Error: unable to update exam.");
		}
	});

}



function setExamId(examId) {
	examID = examId;
}

function setExamTime(eTime) {
	examTime = eTime;
}

function deleteExam(resourceUrl) {
	$.ajax({
		type : "DELETE",
		url : resourceUrl+"/"+examID,
		dataType: "json",
		contentType : "application/json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#exam-edit-result").removeClass("errorblock");
				$("#exam-edit-result").addClass("successblock");
				$("#exam-edit-result").html(response.resultMessage);
			} else if(response != null && response.resultCode == 110) {
				$("#exam-edit-result").removeClass("successblock");
				$("#exam-edit-result").addClass("errorblock");
				$("#exam-edit-result").html(response.resultMessage);
			} else {
				$("#exam-edit-result").removeClass("successblock");
				$("#exam-edit-result").addClass("errorblock");
				$("#exam-edit-result").html("Unable to delete exam.");
			}
			
		},
		error : function(e) {
			$("#exam-edit-result").removeClass("successblock");
			$("#exam-edit-result").addClass("errorblock");
			$("#exam-edit-result").html("Error: unable to delete exam.");
		}
	});
}

function getUserExamList(resourceUrl, div) {

	$.ajax({
		type : "GET",
		url : resourceUrl,
		dataType: "json",
		contentType : "application/json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#"+div).removeClass("errorblock");
				$("#"+div).addClass("successblock");
				
				displayUserExamList(response, div);
				
			} else if(response != null && response.resultCode == 103) {
				$("#"+div).removeClass("successblock");
				$("#"+div).addClass("errorblock");
				$("#"+div).html(response.resultMessage);
			} else {
				$("#"+div).removeClass("successblock");
				$("#"+div).addClass("errorblock");
				$("#"+div).html("Unable to fetch exam list.");
			}
			
		},
		error : function(e) {
			$("#"+div).removeClass("successblock");
			$("#"+div).addClass("errorblock");
			$("#"+div).html("Error: unable to fetch exam list.");
		}
	});
}

function displayUserExamList(data, div) {
	$("#"+div).empty();
	$("#exam-detail-label").show();
	var exList = data.exams;
	setExamListUser(exList);
	
	var $elist = $('<select name="exam-list-user" id="exam-list-user-id" style="margin: 0 auto; width:50%; text-align:center;">');
	for(var i =  0; i < exList.length; i++) {
		$elist.append('<option value='+ i + "---" + exList[i].examId + "---" + exList[i].examTime+ "---" + exList[i].totalQuestion +'>'+exList[i].examName+'</option>');
	}
	$elist.append('</select>');
	$elist.appendTo('#'+div);
	$b = $('<input type="button" value="Select Exam" onclick="selectExam( );"></input>');
	$b.appendTo('#'+div);
	
}

function selectExam( ) {
	var examIndex = $("#exam-list-user-id").val().split("---");
	var examSelect = examList[examIndex[0]];
	setExamId(examIndex[1]);
	setExamTime(examIndex[2]);
	setTotalQuestion(examIndex[3]);
	
	var $eDetail = $('<ul style="text-align:left; margin: 0 auto;">');
	$eDetail.append('<li>Exam name : <b>'+examSelect.examName+'</b> </li>');
	$eDetail.append('<li>Total number of question : <b>'+examSelect.totalQuestion+'</b> </li>');
	$eDetail.append('<li>Answer time per question (in minutes) : <b>'+examSelect.examTime+'</b> </li>');
	$eDetail.append('<li>Exam pass percentage (%) : <b>'+examSelect.passPercentage+'</b> </li>');
	$eDetail.append('<li>Exam description : <b>'+examSelect.examDescription+'</b> </li>');
	
	$eDetail.append('</ul>');
	$("#all-exams-list").empty();
	$eDetail.appendTo('#all-exams-list');
	
	$b = $('<input type="button" value="Start Exam" onclick="startExam('+examIndex[0]+');"></input>');
	$b.appendTo('#all-exams-list');
	
}
function setExamListUser(exList){
	examList = exList;
}

function setTotalQuestion(tQuestion) {
	totalQuestion = tQuestion;
}

function startExam(index){
	$(".user-nav").hide();
	$("#exam-detail-label").empty();
	$('#all-exams-list').empty();
	$('#all-exams-list').hide();
	$("#exam-detail-label").html('<h3>'+examList[index].examName+'</h3>');
	currentQIndex = 0;
	
	$.ajax({
		type : "GET",
		url : baseUrl+"/user/exam/"+examID+"/question/list",
		dataType: "json",
		contentType : "application/json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				questionList =  response.questions;
				
				$("#question-detail-form").show();
				$("#clock").show();
				
				displayExamQuestion();
			} 
		}
	});
}

function displayExamQuestion() {
	$("#question-detail-form").show();
	var q = questionList[currentQIndex];
	setQuestionId(q.questionId);
	displayTimer( );
	
	$("#question-detail-form .questionDescription").html( (currentQIndex+1) + ". &nbsp <pre>" + q.questionDescription + "</pre>");
	$("#question-detail-form .option1").html('<input type="radio" name="correctOption" class="correctOption" value="1" /> &nbsp <pre>' + q.option1 + "</pre>");
	$("#question-detail-form .option2").html('<input type="radio" name="correctOption" class="correctOption" value="2" /> &nbsp <pre>' + q.option2 + "</pre>");
	$("#question-detail-form .option3").html('<input type="radio" name="correctOption" class="correctOption" value="3" /> &nbsp <pre>' + q.option3 + "</pre>");
	$("#question-detail-form .option4").html('<input type="radio" name="correctOption" class="correctOption" value="4" /> &nbsp <pre>' + q.option4 + "</pre>");
}


function displayTimer(){
	var seconds_left = examTime * 60;
	timerId = setInterval(function () {
	    minutes = parseInt(seconds_left / 60);
	    seconds = parseInt(seconds_left % 60);
	    $("#clock").html(minutes + " : " + seconds );  
	    seconds_left = seconds_left-1;
	    
	    if(seconds_left == -1) {
	    	removeClock();
	    	if(isSubmitRequired) {
	    		submitAnswer( );
	    	}
	    }
	    
	}, 1000);
}

function removeClock(){
	clearInterval(timerId);
}
