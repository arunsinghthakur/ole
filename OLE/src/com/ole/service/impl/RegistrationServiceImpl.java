package com.ole.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ole.constant.Constant;
import com.ole.dao.UserDAO;
import com.ole.exception.RegistrationException;
import com.ole.model.Role;
import com.ole.model.User;
import com.ole.service.RegistrationService;
import com.ole.util.Utility;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	private UserDAO userDao;
	
	@Override
	@Transactional
	public void register( User user, Role role)  throws RegistrationException{
		if(Utility.isEmpty(user.getUserName())) {
			throw new RegistrationException(Constant.USERNAME_REQUIRED);
		} else if(Utility.isEmpty(user.getPassword())) {
			throw new RegistrationException(Constant.PASSWORD_REQUIRED);
		} else if(Utility.isEmpty(user.getFirstName())) {
			throw new RegistrationException(Constant.FIRSTNAME_REQUIRED);
		}
		userDao.saveUser(user, role);
	}
}
