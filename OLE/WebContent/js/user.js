function addUser(resourceUrl) {
	var p = new Object();
	p.userName = $("#user-registration-form .userName").val();
	p.password = $("#user-registration-form .password").val();
	p.firstName = $("#user-registration-form .firstName").val();
	p.lastName = $("#user-registration-form .lastName").val();
	p.address = $("#user-registration-form .address").val();
	p.email = $("#user-registration-form .email").val();
	p.mobile = $("#user-registration-form .mobile").val();
	p.active = $("#user-registration-form .active").val();

	
	$.ajax({
		type : "POST",
		url : resourceUrl,
		dataType: "json",
		contentType : "application/json",
		data : JSON.stringify(p),
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#registration-result").removeClass("errorblock");
				$("#registration-result").addClass("successblock");
				$("#registration-result").html(response.resultMessage);
			} else if(response != null && (response.resultCode == 100 || response.resultCode == 104)) {
				$("#registration-result").removeClass("successblock");
				$("#registration-result").addClass("errorblock");
				$("#registration-result").html(response.resultMessage);
			} else {
				$("#registration-result").removeClass("successblock");
				$("#registration-result").addClass("errorblock");
				$("#registration-result").html("Sorry Unable to register.");
			}
			
		},
		error : function(e) {
			$("#registration-result").removeClass("successblock");
			$("#registration-result").addClass("errorblock");
			$("#registration-result").html("Error: unable to register.");
		}
	});
}


function editUser(resourceUrl) {
	
	var p = new Object();
	p.userName = $("#profile-update-form .userName").val();
	p.password = $("#profile-update-form .password").val();
	p.firstName = $("#profile-update-form .firstName").val();
	p.lastName = $("#profile-update-form .lastName").val();
	p.address = $("#profile-update-form .address").val();
	p.email = $("#profile-update-form .email").val();
	p.mobile = $("#profile-update-form .mobile").val();
	p.active = $("#profile-update-form .active").val();
	
	$.ajax({
		type : "PUT",
		url : resourceUrl,
		dataType: "json",
		contentType : "application/json",
		data : JSON.stringify(p),
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#profile-edit-result").removeClass("errorblock");
				$("#profile-edit-result").addClass("successblock");
				$("#profile-edit-result").html(response.resultMessage);
			} else if(response != null && (response.resultCode == 101 || response.resultCode == 104 || response.resultCode == 105 || response.resultCode == 101)) {
				$("#profile-edit-result").removeClass("successblock");
				$("#profile-edit-result").addClass("errorblock");
				$("#profile-edit-result").html(response.resultMessage);
			} else {
				$("#profile-edit-result").removeClass("successblock");
				$("#profile-edit-result").addClass("errorblock");
				$("#profile-edit-result").html("Unable to update profile.");
			}
			
		},
		error : function(e) {
			$("#profile-edit-result").removeClass("successblock");
			$("#profile-edit-result").addClass("errorblock");
			$("#profile-edit-result").html("Error: unable to update profile.");
		}
	});
}

function deleteUser(resourceUrl) {
	
	$.ajax({
		type : "DELETE",
		url : resourceUrl,
		dataType: "json",
		success : function(response) {
			if(response != null && response.resultCode == 0) {
				$("#profile-edit-result").removeClass("errorblock");
				$("#profile-edit-result").addClass("successblock");
				$("#profile-edit-result").html(response.resultMessage);
			} else if(response != null && (response.resultCode == 101 || response.resultCode == 104 || response.resultCode == 105 || response.resultCode == 112)) {
				$("#profile-edit-result").removeClass("successblock");
				$("#profile-edit-result").addClass("errorblock");
				$("#profile-edit-result").html(response.resultMessage);
			} else {
				$("#profile-edit-result").removeClass("successblock");
				$("#profile-edit-result").addClass("errorblock");
				$("#profile-edit-result").html("Unable to delete profile.");
			}
			
		},
		error : function(e) {
			$("#profile-edit-result").removeClass("successblock");
			$("#profile-edit-result").addClass("errorblock");
			$("#profile-edit-result").html("Error: unable to delete profile.");
		}
	});
}