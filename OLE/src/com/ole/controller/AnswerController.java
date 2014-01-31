package com.ole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ole.constant.Constant;
import com.ole.constant.ResponseCode;
import com.ole.exception.AnswerException;
import com.ole.exception.AuthorizationException;
import com.ole.exception.UserException;
import com.ole.model.Answer;
import com.ole.model.AnswerResponse;
import com.ole.model.BaseResponse;
import com.ole.model.Exam;
import com.ole.model.Question;
import com.ole.model.User;
import com.ole.service.AnswerService;

@Controller
@RequestMapping("/{role}")
public class AnswerController extends BaseController{
	
	@Autowired
	private AnswerService answerService;
	
	@ResponseBody
	@RequestMapping(value = "/{userId}/exam/{examId}/question/{questionId}/optionSelect/{optionSelect}", method = RequestMethod.POST)
	public BaseResponse saveAnswer(@PathVariable("examId") Long examId, @PathVariable("userId") Long userId, @PathVariable("questionId") Long questionId, @PathVariable("optionSelect") int optionSelect, @PathVariable("role") String role) {
		BaseResponse response = new BaseResponse();
		Answer answer = new Answer();
		Exam exam = new Exam();
		Question question = new Question();
		User user = new User();
		try {
			User u = currentLoggedInUser();
			isValidUser(u, role);
			if(userId == null || userId <=0) {
				userId = u.getUserId();
			}
			
			user.setUserId(userId);
			exam.setExamId(examId);
			question.setQuestionId(questionId);
			
			answer.setUser(user);
			answer.setExam(exam);
			answer.setQuestion(question);
			answer.setOptionSelect(optionSelect);
			
			answerService.saveAnswer(answer);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.ANSWER_ADDED);
		} catch (AnswerException e) {
			response.setResultCode(ResponseCode.ANSWER_ADD_ERROR_CODE);
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
	@RequestMapping(value = "/user/{userId}/exam/{examId}/answer/list", method = RequestMethod.GET)
	public AnswerResponse getAllAnswer(@PathVariable("role") String role, @PathVariable("examId") Long examId, @PathVariable("userId") Long userId) {
		AnswerResponse response = new AnswerResponse();
		boolean isInternalRequest = false;
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			if(userId == null || userId <=0) {
				userId = user.getUserId();
				isInternalRequest = true;
			}
			response.setAnswers(answerService.getAllAnswer(userId, examId, isInternalRequest));
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.ANSWER_FETCH);
		} catch (AnswerException e) {
			response.setResultCode(ResponseCode.ANSWER_FETCH_ERROR_CODE);
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
	@RequestMapping(value="/{userId}/exam/{examId}", method = RequestMethod.DELETE)
	public BaseResponse deleteExamAnswers(@PathVariable("examId") long examId, @PathVariable("userId") long userId, @PathVariable("role") String role) {

		BaseResponse response = new BaseResponse();
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			answerService.deleteAllAnswer(userId, examId);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.ANSWER_DELETED);
		} catch (AnswerException e) {
			response.setResultCode(ResponseCode.ANSWER_DELETE_ERROR_CODE);
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
}
