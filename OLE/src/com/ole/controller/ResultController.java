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
import com.ole.exception.ExamException;
import com.ole.exception.ResultException;
import com.ole.exception.UserException;
import com.ole.model.ResultResponse;
import com.ole.model.User;
import com.ole.service.ResultService;

@Controller
@RequestMapping("/{role}")
public class ResultController extends BaseController {
	
	@Autowired
	private ResultService resultService;
	
	@ResponseBody
	@RequestMapping(value="/{userId}/exam/{examId}/result", method=RequestMethod.POST)
	public ResultResponse calculateExamResult(@PathVariable("role") String role, @PathVariable("userId") Long userId, @PathVariable("examId") Long examId) {
		ResultResponse response = new ResultResponse();
		boolean isInternalRequest = false;
		try {
			User u = currentLoggedInUser();
			isValidUser(u, role);
			if(userId == null || userId <=0) {
				userId = u.getUserId();
				isInternalRequest = true;
			}
			response = resultService.calculateResult(userId, examId, isInternalRequest);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.RESULT_ADDED);
		} catch (ResultException e) {
			response.setResultCode(ResponseCode.RESULT_ADD_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AnswerException e) {
			response.setResultCode(ResponseCode.RESULT_ADD_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (ExamException e) {
			response.setResultCode(ResponseCode.RESULT_ADD_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}
}
