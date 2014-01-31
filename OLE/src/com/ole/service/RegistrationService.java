package com.ole.service;

import com.ole.exception.RegistrationException;
import com.ole.model.Role;
import com.ole.model.User;

public interface RegistrationService {

	void register(User user, Role role) throws RegistrationException;

}