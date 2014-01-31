package com.ole.controller;

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
import com.ole.exception.ProfileException;
import com.ole.exception.UserException;
import com.ole.model.BaseResponse;
import com.ole.model.User;
import com.ole.model.UserResponse;
import com.ole.service.ProfileService;

@Controller
@RequestMapping("/{role}/profile")
public class ProfileController extends BaseController{

	@Autowired
	private ProfileService profileService;

	@RequestMapping(method = RequestMethod.GET)
	public String editView(Model model, @PathVariable("role") String role) {
		String viewType = null;
		try {
			viewType = role.equalsIgnoreCase(Constant.ROLE_ADMIN_KEY) ? "admin/edit-profile-admin" : "user/edit-profile-user";
			User user = currentLoggedInUser();
			isValidUser(user, role);
			model.addAttribute("user", user);
			model.addAttribute("firstName", user.getFirstName());
		} catch (UserException e) {
			model.addAttribute("profile-edit-error", e.getMessage());
		} catch (AuthorizationException e) {
			model.addAttribute("un-auth-access", e.getMessage());
			viewType = "common/un-authorized-access";
		}
		return viewType;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public BaseResponse editProfile(@RequestBody User user, @PathVariable("role") String role) {
		BaseResponse response = new BaseResponse();
		try {
			User tempUser = currentLoggedInUser();
			isValidUser(tempUser, role);
			user.setUserId(tempUser.getUserId());
			profileService.editProfile(user, tempUser.getRole());
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.PROFILE_EDIT_SUCCESS_MESSAGE);
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (ProfileException e) {
			response.setResultCode(ResponseCode.PROFILE_EDIT_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/{userId}",method = RequestMethod.DELETE)
	public BaseResponse deleteProfile( @PathVariable("userId") Long userId, @PathVariable("role") String role) {
		BaseResponse response = new BaseResponse();
		try {
			User tempUser = currentLoggedInUser();
			isValidUser(tempUser, role);
			if(userId == null || userId < 1) {
				profileService.deleteProfile(tempUser.getUserId());
			}
			profileService.deleteProfile(userId);
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.PROFILE_DELETED);
		} catch (UserException e) {
			response.setResultCode(ResponseCode.USER_NOT_EXIST_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		} catch (AuthorizationException e) {
			response.setResultCode(ResponseCode.UN_AUTHORIZED_ACCESS_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}catch (ProfileException e) {
			response.setResultCode(ResponseCode.PROFILE_DELETE_ERROR_CODE);
			response.setResultMessage(e.getMessage());
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public UserResponse getAllUser(@PathVariable("role") String role) {
		UserResponse response = new UserResponse();
		try {
			User tempUser = currentLoggedInUser();
			isValidUser(tempUser, role);
			response.setUsers(profileService.getAllUser());
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.USER_NOT_FOUND);
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
	@RequestMapping(value = "/userId/{userId}", method = RequestMethod.GET)
	public UserResponse getUserById(@PathVariable("role") String role, @PathVariable("userId") Long userId) {
		UserResponse response = new UserResponse();
		try {
			User tempUser = currentLoggedInUser();
			isValidUser(tempUser, role);
			response.setUser(profileService.getUser(userId));
			response.setResultCode(ResponseCode.SUCCESS_CODE);
			response.setResultMessage(Constant.USER_NOT_FOUND);
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
