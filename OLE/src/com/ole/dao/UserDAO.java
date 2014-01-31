package com.ole.dao;

import java.util.List;

import com.ole.exception.ProfileException;
import com.ole.exception.RegistrationException;
import com.ole.exception.UserException;
import com.ole.model.Role;
import com.ole.model.User;

public interface UserDAO {

	boolean saveUser(User user, Role role)  throws RegistrationException;
	boolean editUser(User user, Role role)  throws ProfileException;
	boolean deleteUser(long userId) throws ProfileException;
	User getUser( long userId)throws UserException;
	User getUser( String userName)throws UserException;
	List<User> getAllUser()throws UserException;
}
