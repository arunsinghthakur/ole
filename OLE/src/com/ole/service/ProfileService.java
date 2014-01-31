package com.ole.service;

import java.util.List;

import com.ole.exception.ProfileException;
import com.ole.exception.UserException;
import com.ole.model.Role;
import com.ole.model.User;

public interface ProfileService {
	boolean editProfile(User user, Role role) throws ProfileException;
	boolean deleteProfile(long userId) throws ProfileException;
	User getUser(String username ) throws UserException;
	User getUser(long userId ) throws UserException;
	List<User> getAllUser(  ) throws UserException;
}
