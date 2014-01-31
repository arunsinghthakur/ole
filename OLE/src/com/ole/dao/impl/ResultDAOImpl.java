package com.ole.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ole.constant.Constant;
import com.ole.dao.DatabaseConnection;
import com.ole.dao.ResultDAO;
import com.ole.exception.ResultException;
import com.ole.model.Result;
import com.ole.util.Utility;

@Repository
public class ResultDAOImpl extends DatabaseConnection implements ResultDAO{

	@Override
	public boolean saveResult(Result result) throws ResultException{
		Transaction tx = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			session.save(result);
			tx.commit();
		} catch(HibernateException x) {
			Utility.rollbackTransaction(tx);
			throw new ResultException(Constant.RESULT_NOT_ADDED);
		} finally {
			Utility.closeSession(session);
		}
		return true;
	}

	

}
