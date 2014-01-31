package com.ole.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.ole.constant.Constant;
import com.ole.exception.AuthorizationException;
import com.ole.exception.UserException;
import com.ole.model.User;
import com.ole.service.ProfileService;

public abstract class BaseController {
	
	@Autowired
	private ProfileService profileService;
	
	
	public User currentLoggedInUser() throws UserException {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		return profileService.getUser((String) authenticate.getPrincipal());
	}
	
	public void isValidUser(User user, String role) throws AuthorizationException{
		if(role == null || !getRoleKey(user.getRole().getRoleName()).equalsIgnoreCase(role)) {
			throw new AuthorizationException(Constant.UN_AUTHORIZED_ACCESS);
		}
	}
	
	private String getRoleKey(String role) {
		if(role.equalsIgnoreCase(Constant.ROLE_ADMIN)){
			return Constant.ROLE_ADMIN_KEY;
		} else if(role.equalsIgnoreCase(Constant.ROLE_USER)){
			return Constant.ROLE_USER_KEY;
		} else {
			return "";
		}
	}
	
	public boolean isFirstUserRegistration(Model model) throws UserException {
		boolean flag = false;
		if(model.containsAttribute("IS_FIRST_REGISTRATION")) {
			Map<String, Object> map = model.asMap();
			if(map.get("IS_FIRST_REGISTRATION") == null ) {
				flag = true;
			} else if(profileService.getAllUser().size() <=0) {
				flag = true;
			} else {
				flag = false;
			}
		} else if(profileService.getAllUser().size() <=0) {
			flag = true;
		} else {
			flag = false;
		}
		
		return flag;
	}
}
