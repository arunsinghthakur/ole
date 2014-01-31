package com.ole.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.ole.constant.Constant;
import com.ole.service.LoginService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private LoginService loginService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		User user = (User) loginService.loadUserByUsername(authentication.getName());
		if(user == null) {
			throw new BadCredentialsException(Constant.USER_NOT_FOUND);
		} else if(!user.getPassword().equals(authentication.getCredentials())) {
			throw new BadCredentialsException(Constant.USER_NOT_FOUND);
		}
		
		Collection<GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), authorities );
	}

	@Override
	public boolean supports(Class<? extends Object> class1) {
		return true;
	}

}
