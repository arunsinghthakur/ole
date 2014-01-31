package com.ole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ole.constant.Constant;
import com.ole.constant.ResponseCode;
import com.ole.exception.AuthorizationException;
import com.ole.exception.RegistrationException;
import com.ole.exception.UserException;
import com.ole.model.BaseResponse;
import com.ole.model.Role;
import com.ole.model.User;
import com.ole.service.RegistrationService;

@Controller
@RequestMapping("/register")
@SessionAttributes("IS_FIRST_REGISTRATION")
public class RegistrationController extends BaseController {
	
	@Autowired
	private RegistrationService regService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String registration( User user) {
		return "common/register";
	}
	
	@RequestMapping(value = "/{role}", method = RequestMethod.GET)
	public String registerAdmin(Model model, @PathVariable("role") String role) {
		String viewType = null;
		try {
			User user = currentLoggedInUser();
			isValidUser(user, role);
			model.addAttribute("firstName", user.getFirstName());
			viewType = "admin/add-admin";
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
	@RequestMapping(method = RequestMethod.POST)
	public BaseResponse register(@RequestBody User user, Role role, Model model) {
		BaseResponse response = new BaseResponse();
		try {
			if(isFirstUserRegistration(model)) {
				model.addAttribute("IS_FIRST_REGISTRATION", false);
				role.setRoleName(Constant.ROLE_ADMIN);
			}
			regService.register(user, role);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.REGISTRATION_SUCCESS_MESSAGE);
		} catch (RegistrationException e) {
			response.setResultCode(ResponseCode.REGISTRATION_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (UserException e) {
			response.setResultCode(ResponseCode.REGISTRATION_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/{roleId}", method = RequestMethod.POST)
	public BaseResponse registerAdmin(@RequestBody User user, Role role, @PathVariable("roleId") String roleId) {

		BaseResponse response = new BaseResponse();
		try {
			User temp = currentLoggedInUser();
			isValidUser(temp, roleId);
			role.setRoleName(Constant.ROLE_ADMIN);
			regService.register(user, role);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.REGISTRATION_SUCCESS_MESSAGE);
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (RegistrationException e) {
			response.setResultCode(ResponseCode.REGISTRATION_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}
}
