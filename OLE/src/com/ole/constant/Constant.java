package com.ole.constant;

public interface Constant {
	String USER_NOT_FOUND = "User not found. Wrong username or password.";
	String DATABASE_ERROR = "Database error.";
	String USERNAME_EXIST = "Username already in use.";
	String USERNAME_REQUIRED = "Username required.";
	String PASSWORD_REQUIRED = "Password required.";
	String FIRSTNAME_REQUIRED = "Firstname required.";
	
	String ROLE_USER_KEY = "user";
	String ROLE_ADMIN_KEY = "admin";
	String ROLE_USER = "ROLE_USER";
	String ROLE_ADMIN = "ROLE_ADMIN";
	String UN_AUTHORIZED_ACCESS = "You don't have permissions to access this page.";
	
	String USER_NOT_DELETE = "Unable to delete user.";
	String USER_NOT_UPDATE = "Unable to update user.";
	String NO_USER_FOUND = "User not found.";
	String UNABLE_TO_GET_USER = "Unable to fetch user.";
	String EDIT_SUCCESSFULLY = "Profile edited successfully.";
	
	String EXAM_EXCEPTION = " General exam add exception.";
	String EXAM_ALREADY_EXIST = "Exam already added.";
	String EXAM_NOT_UPDATE = "Unable to update exam.";
	String EXAM_NOT_DELETE = "Unable to delete exam.";
	String NO_EXAM_FOUND = "Exam not found.";
	
	String EXAM_NOT_ADDED = "Unable to add exam.";
	String EXAM_REQUIRED = "Examname required.";
	String EXAM_QUESTION_REQUIRED = "Exam questions required.";
	String EXAM_TIME_REQUIRED = "Exam time required.";
	String EXAM_PASS_PERCENTAGE_REQUIRED = "Valid exam pass percentage required.";
	String EXAM_ADDED = "Exam added successfully.";
	
	String EXAM_UPDATED = "Exam updated successfully.";
	String EXAM_LIST_FETCH = "Following exam(s) found.";
	String NO_EXAM_ADDED = "Not exam added yet.";
	String EXAM_AVAILABLE = "AVAILABLE";
	String EXAM_UNAVAILABLE = "UNAVAILABLE";
	
	String REG_SUCCESSFUL = "Profile has been registered successfully.";
	String REGISTRATION_EXCEPTION = "Unable to register user.";
	String REGISTRATION_SUCCESS_MESSAGE = "Registration has been done successfully.";
	String PROFILE_EDIT_SUCCESS_MESSAGE = "Profile has been update successfully.";
	
	String QUESTION_EXCEPTION = " General question add exception.";
	String QUESTION_NOT_ADDED = "Unable to add question.";
	String QUESTION_DESC_REQUIRED = "Question description required.";
	String OPTIONS_REQUIRED = "Options required.";
	String QUESTION_ADDED = "Question added successfully.";
	
	String QUESTION_LIST_FETCH = "Following question(s) found.";
	String NO_QUESTION_ADDED = "Not question added yet.";
	String QUESTION_UPDATED = "Question updated successfully.";
	String QUESTION_NOT_UPDATE = "Unable to update question.";
	String QUESTION_NOT_DELETE = "Unable to delete question.";
	
	String NO_QUESTION_FOUND = "Question not found.";
	String EXAM_DELETED ="Exam deleted successfully.";
	String QUESTION_DELETED ="Question deleted successfully.";
	String PROFILE_DELETED ="Profile deleted successfully.";
	String PROFILE_NOT_DELETED ="Unable to delete profile.";
	
	String GENERAL_EXCEPTION = "General exception occur.";
	
	String ANSWER_NOT_ADDED = "Unable to submit answer.";
	String ANSWER_NOT_DELETED = "Unable to delete answer.";
	String ANSWER_ADDED = "Answer submitted successfully.";
	String NO_ANSWER_FOUND = "Answer(s) not found.";
	String ANSWER_FETCH = "Following answer(s) found.";
	
	String ANSWER_DELETED ="Answer deleted successfully.";
	String RESULT_NOT_ADDED = "Unable to submit result.";
	String RESULT_ADDED = "Result submitted successfully.";
	String NO_RESULT_FOUND = "Result(s) not found.";
	String RESULT_FETCH = "Following result(s) found.";
	
	String NO_HISTORY_FOUND = "No history found.";
	String HISTORY_FETCH = "Following history found.";
}
