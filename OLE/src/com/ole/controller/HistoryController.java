package com.ole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ole.constant.Constant;
import com.ole.constant.ResponseCode;
import com.ole.exception.AuthorizationException;
import com.ole.exception.HistoryException;
import com.ole.exception.UserException;
import com.ole.model.HistoryResponse;
import com.ole.model.User;
import com.ole.service.HistoryService;

@Controller
@RequestMapping("/{role}")
public class HistoryController extends BaseController{
	
	@Autowired
	private HistoryService historyService;
	
	
	@RequestMapping(value = "/exam/history", method = RequestMethod.GET)
	public String historyView(Model model, @PathVariable("role") String role) {
		String viewType = null;
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			model.addAttribute("firstName", user.getFirstName());
			viewType = "user/history";
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
	@RequestMapping(value = "/{userId}/exam/history", method = RequestMethod.GET)
	public HistoryResponse getAllExam(@PathVariable("role") String role, @PathVariable("userId") Long userId) {

		HistoryResponse response = new HistoryResponse();
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			if(userId == null || userId <=0) {
				userId = user.getUserId();
			}
			response = historyService.getHistory(userId);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.HISTORY_FETCH);

		} catch (HistoryException e) {
			response.setResultCode(ResponseCode.HISTORY_FETCH_ERROR_CODE);
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
