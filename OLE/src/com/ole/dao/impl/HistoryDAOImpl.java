package com.ole.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ole.constant.Constant;
import com.ole.dao.DatabaseConnection;
import com.ole.dao.HistoryDAO;
import com.ole.exception.HistoryException;
import com.ole.model.Result;
import com.ole.util.Utility;

@Repository
public class HistoryDAOImpl extends DatabaseConnection implements HistoryDAO {

	@Override
	public List<Result> getHistory(long userId) throws HistoryException {
		Transaction tx = null;
		List<Result> temp = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			temp = (List<Result>) session.createQuery("from Result where user_id= :userId").setParameter("userId", userId).list();
			tx.commit();
			if (null == temp) {
				throw new HistoryException(Constant.NO_HISTORY_FOUND);
			} else if (temp.isEmpty()) {
				throw new HistoryException(Constant.NO_HISTORY_FOUND);
			}
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			throw new HistoryException(Constant.NO_HISTORY_FOUND);
		} finally {
			Utility.closeSession(session);
		}
		return temp;
	}

}
