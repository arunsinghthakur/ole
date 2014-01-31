package com.ole.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.ole.constant.Constant;
import com.ole.dao.DatabaseConnection;
import com.ole.dao.UserDAO;
import com.ole.exception.ProfileException;
import com.ole.exception.RegistrationException;
import com.ole.exception.UserException;
import com.ole.model.Role;
import com.ole.model.User;
import com.ole.util.Utility;

@Repository
public class UserDAOImpl extends DatabaseConnection implements UserDAO {

	@Override
	public boolean saveUser(User user, Role role) throws RegistrationException {
		Transaction tx = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			user.setRole(role);
			role.setUser(user);
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			e.printStackTrace();
			throw new RegistrationException(Constant.USERNAME_EXIST);
		} finally {
			Utility.closeSession(session);
		}
		return true;
	}

	@Override
	public boolean deleteUser(long userId) throws ProfileException {
		Transaction tx = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			User tempUser = (User) session.get(User.class, userId);
			if (null == tempUser) {
				throw new ProfileException(Constant.NO_USER_FOUND);
			}
			session.delete(tempUser);
			tx.commit();
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			throw new ProfileException(Constant.USER_NOT_DELETE);
		} finally {
			Utility.closeSession(session);
		}
		return true;
	}

	@Override
	public User getUser(long userId) throws UserException {
		Transaction tx = null;
		User tempUser = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			tempUser = (User) session
					.createQuery("from User where userId= :userId")
					.setParameter("userId", userId).uniqueResult();
			tx.commit();
			if (null == tempUser) {
				throw new UserException(Constant.NO_USER_FOUND);
			}
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			throw new UserException(Constant.UNABLE_TO_GET_USER);
		} finally {
			Utility.closeSession(session);
		}
		return tempUser;
	}

	@Override
	public User getUser(String userName) throws UserException {
		Transaction tx = null;
		User tempUser = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			tempUser = (User) session
					.createQuery("from User where username= :userName")
					.setParameter("userName", userName).uniqueResult();
			tx.commit();
			if (null == tempUser) {
				throw new UserException(Constant.NO_USER_FOUND);
			}
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			throw new UserException(Constant.UNABLE_TO_GET_USER);
		} finally {
			Utility.closeSession(session);
		}
		return tempUser;
	}

	@Override
	public List<User> getAllUser() throws UserException {
		Transaction tx = null;
		List<User> tempUser = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			tempUser = session.createQuery("from User").list();
			tx.commit();
			if (null == tempUser) {
				throw new UserException(Constant.NO_USER_FOUND);
			}
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			throw new UserException(Constant.UNABLE_TO_GET_USER);
		} finally {
			Utility.closeSession(session);
		}
		return tempUser;
	}

	@Override
	public boolean editUser(User user, Role role) throws ProfileException {
		Transaction tx = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			user.setRole(role);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			throw new ProfileException(Constant.USER_NOT_UPDATE);
		} finally {
			Utility.closeSession(session);
		}
		return true;
	}

}
