package com.ole.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ole.constant.Constant;
import com.ole.exception.UserException;
import com.ole.model.User;

@Controller
public class LoginController extends BaseController{
	
	@RequestMapping(value="/login" , method=RequestMethod.GET)
	public String login() {
		return "common/login";
	}
	
	@RequestMapping(value="/loginfailed" , method=RequestMethod.GET)
	public String loginFail(Model model) {
		model.addAttribute("error", "error");
		return "common/login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout( ) {
		return "common/login";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome( Model model) {
		User user = null;
		String viewType;
		try {
			user = currentLoggedInUser();
			model.addAttribute("firstName", user.getFirstName());
			if(Constant.ROLE_ADMIN.equalsIgnoreCase(user.getRole().getRoleName())) {
				viewType = "admin/admin";
			} else {
				viewType = "user/user";
			}
		} catch (UserException e) {
			viewType = "common/un-authorized-access";
		}
		return viewType;
	}
	
}

