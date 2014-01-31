package com.ole.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DatabaseConnection {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
