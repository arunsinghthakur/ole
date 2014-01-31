package com.ole.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ole.constant.Constant;
import com.ole.dao.UserDAO;
import com.ole.exception.ProfileException;
import com.ole.exception.UserException;
import com.ole.model.Role;
import com.ole.model.User;
import com.ole.service.ProfileService;
import com.ole.util.Utility;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public boolean editProfile(User user, Role role) throws ProfileException {
		if (Utility.isEmpty(user.getUserName())) {
			throw new ProfileException(Constant.USERNAME_REQUIRED);
		} else if (Utility.isEmpty(user.getPassword())) {
			throw new ProfileException(Constant.PASSWORD_REQUIRED);
		} else if (Utility.isEmpty(user.getFirstName())) {
			throw new ProfileException(Constant.FIRSTNAME_REQUIRED);
		}
		return userDAO.editUser(user, role);
	}

	@Override
	@Transactional
	public boolean deleteProfile(long userId) throws ProfileException {
		return userDAO.deleteUser(userId);
	}

	@Override
	@Transactional
	public User getUser(String username) throws UserException {
		return userDAO.getUser(username);
	}

	@Override
	@Transactional
	public List<User> getAllUser() throws UserException {
		return userDAO.getAllUser();
	}

	@Override
	@Transactional
	public User getUser(long userId) throws UserException {
		return userDAO.getUser(userId);
	}

}
