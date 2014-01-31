package com.ole.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ole.constant.Constant;
import com.ole.dao.UserDAO;
import com.ole.exception.UserException;
import com.ole.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService , Serializable{

	private static final long serialVersionUID = 2720075130521122390L;
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public User loadUserByUsername(String username) {
		com.ole.model.User user = null;
		try {
			user = userDAO.getUser(username);
			if (null == user) {
				throw new UsernameNotFoundException(Constant.USER_NOT_FOUND);
			}
		} catch (DataAccessException unfe) {
			throw new UsernameNotFoundException(Constant.DATABASE_ERROR);
		} catch (UserException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
		return getUserDetails(user);
	}

	@SuppressWarnings("deprecation")
	private User getUserDetails(final com.ole.model.User user) {
		GrantedAuthority[] authorities = new GrantedAuthority[1];
		authorities[0] = new GrantedAuthority() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return user.getRole().getRoleName();
			}
		};
		User userDetails = new User(user.getUserName(), user.getPassword(), user.isActive(), user.isActive(), user.isActive(), user.isActive(), authorities);
		return userDetails;
	}

}
