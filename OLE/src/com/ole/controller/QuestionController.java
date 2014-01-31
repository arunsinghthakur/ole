package com.ole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ole.constant.Constant;
import com.ole.constant.ResponseCode;
import com.ole.exception.AuthorizationException;
import com.ole.exception.ExamException;
import com.ole.exception.QuestionException;
import com.ole.exception.UserException;
import com.ole.model.BaseResponse;
import com.ole.model.Exam;
import com.ole.model.Question;
import com.ole.model.QuestionResponse;
import com.ole.model.User;
import com.ole.service.ExamService;
import com.ole.service.QuestionService;

@Controller
@RequestMapping("/{role}")
public class QuestionController extends BaseController{
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private ExamService examService;

	@RequestMapping(value = "/question/{view}",method = RequestMethod.GET)
	public String questionView(Model model, @PathVariable("role") String role ) {
		String viewType = null;
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			model.addAttribute("firstName", user.getFirstName());
			viewType = "admin/add-question";
		} catch (UserException e) {
			model.addAttribute("un-auth-access", e.getMessage());
			viewType = "common/un-authorized-access";
		} catch (AuthorizationException e) {
			model.addAttribute("un-auth-access", e.getMessage());
			viewType = "common/un-authorized-access";
		}
		
		return viewType;
	}
	
	@RequestMapping(value = "/question/eview", method = RequestMethod.GET)
	public String questionEditView(Model model, @PathVariable("role") String role) {
		String viewType = null;
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			model.addAttribute("firstName", user.getFirstName());
			viewType = "admin/edit-question";
		} catch (UserException e) {
			model.addAttribute("un-auth-access", e.getMessage());
			viewType = "common/un-authorized-access";
		} catch (AuthorizationException e) {
			model.addAttribute("un-auth-access", e.getMessage());
			viewType = "common/un-authorized-access";
		}

		return viewType;
	}
	
	@ResponseBody
	@RequestMapping(value = "/exam/{examId}/question", method = RequestMethod.POST)
	public BaseResponse addQuestion(@RequestBody Question question , @PathVariable("role") String role, @PathVariable("examId") long examId) {
		
		BaseResponse response = new BaseResponse();
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			
			Exam exam = new Exam();
			exam.setExamId(examId);
			question.setExam(exam );
			questionService.addQuestion(question);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.QUESTION_ADDED);
		} catch (QuestionException e) {
			response.setResultCode(ResponseCode.QUESTION_ADD_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (ExamException e) {
			response.setResultCode(ResponseCode.QUESTION_ADD_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/exam/{examId}/question/{questionId}", method = RequestMethod.PUT)
	public BaseResponse editQuestion(@PathVariable("role") String role, @PathVariable("examId") Long examId, @PathVariable("questionId") Long questionId, @RequestBody Question question) {
		BaseResponse response = new BaseResponse();
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			
			Exam exam = new Exam();
			exam.setExamId(examId);
			question.setExam(exam );
			questionService.editQuestion(question);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.QUESTION_UPDATED);
		} catch (QuestionException e) {
			response.setResultCode(ResponseCode.QUESTION_UPDATE_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "exam/{examId}/question/list", method = RequestMethod.GET)
	public QuestionResponse getAllQuestion(@PathVariable("role") String role, @PathVariable("examId") long examId) {

		QuestionResponse response = new QuestionResponse();
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			boolean filter = true;
			Exam exam = examService.getExam(examId);;
			if (role.equalsIgnoreCase(Constant.ROLE_ADMIN_KEY)) {
				filter = false;
			} else {
				if(exam.getExamStatus().equalsIgnoreCase(Constant.EXAM_UNAVAILABLE)) {
					response.setResultCode(ResponseCode.EXAM_FETCH_ERROR_CODE);
					response.setResultMessage(Constant.NO_EXAM_FOUND);
					return response;
				}
			}
			List<Question> questions = questionService.getAllQuestion(filter, exam.getTotalQuestion(), examId);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.QUESTION_LIST_FETCH);
			response.setQuestions(questions);

		} catch (ExamException e) {
			response.setResultCode(ResponseCode.EXAM_FETCH_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (QuestionException e) {
			response.setResultCode(ResponseCode.QUESTION_FETCH_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}

	
	@ResponseBody
	@RequestMapping(value = "exam/{examId}/question/{questionId}", method = RequestMethod.GET)
	public QuestionResponse getQuestion(@PathVariable("role") String role, @PathVariable("examId") long examId, @PathVariable("questionId") long questionId) {

		QuestionResponse response = new QuestionResponse();
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			Exam exam = null;
			if (role.equalsIgnoreCase(Constant.ROLE_USER_KEY)) {
				exam = examService.getExam(examId);
				if(exam.getExamStatus().equalsIgnoreCase(Constant.EXAM_UNAVAILABLE)) {
					response.setResultCode(ResponseCode.EXAM_FETCH_ERROR_CODE);
					response.setResultMessage(Constant.NO_EXAM_FOUND);
					return response;
				}
			}
			Question question = questionService.getQuestion(questionId);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.QUESTION_LIST_FETCH);
			response.setQuestion(question);

		} catch (ExamException e) {
			response.setResultCode(ResponseCode.EXAM_FETCH_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (QuestionException e) {
			response.setResultCode(ResponseCode.QUESTION_FETCH_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}

	
	@ResponseBody
	@RequestMapping(value = "/exam/{examId}/question/{questionId}", method = RequestMethod.DELETE)
	public BaseResponse deleteQuestion(@PathVariable("role") String role, @PathVariable("examId") Long examId, @PathVariable("questionId") Long questionId) {
		BaseResponse response = new BaseResponse();
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			
			questionService.deleteQuestion(examId, questionId);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.QUESTION_DELETED);
		} catch (QuestionException e) {
			response.setResultCode(ResponseCode.QUESTION_DELETE_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}  catch (ExamException e) {
			response.setResultCode(ResponseCode.QUESTION_DELETE_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}
	
}
