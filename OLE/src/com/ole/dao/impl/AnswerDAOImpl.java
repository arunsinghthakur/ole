package com.ole.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ole.constant.Constant;
import com.ole.dao.AnswerDAO;
import com.ole.dao.DatabaseConnection;
import com.ole.exception.AnswerException;
import com.ole.model.Answer;
import com.ole.util.Utility;

@Repository
public class AnswerDAOImpl extends DatabaseConnection implements AnswerDAO {

	@Override
	public boolean saveAnswer(Answer answer) throws AnswerException{
		Transaction tx = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			session.save(answer);
			tx.commit();
		} catch(HibernateException x) {
			Utility.rollbackTransaction(tx);
			throw new AnswerException(Constant.ANSWER_NOT_ADDED);
		} finally {
			Utility.closeSession(session);
		}
		return true;
	}

	@Override
	public List<Answer> getAllAnswer(long userId, long examId, boolean isInternalRequest) throws AnswerException {
		Transaction tx = null;
		List<Answer> temp = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			temp = (List<Answer>) session.createQuery("from Answer where user_id= :userId and exam_id=:examId").setParameter("userId", userId).setParameter("examId", examId).list();
			tx.commit();
			if((null == temp && isInternalRequest) || (temp.isEmpty() && isInternalRequest) ) {
				return new ArrayList<Answer>();
			} else if (null == temp) {
				throw new AnswerException(Constant.NO_ANSWER_FOUND);
			} else if (temp.isEmpty()) {
				throw new AnswerException(Constant.NO_ANSWER_FOUND);
			}
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			throw new AnswerException(Constant.NO_ANSWER_FOUND);
		} finally {
			Utility.closeSession(session);
		}
		return temp;
	}

	@Override
	public boolean deleteAllAnswer(long userId, long examId) throws AnswerException {
		Transaction tx = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			List<Answer> temp = (List<Answer>) session.createQuery("from Answer where user_id= :userId and exam_id=:examId").setParameter("userId", userId).setParameter("examId", examId).list();
			if (null != temp && !temp.isEmpty()) {
				
				for(Answer a : temp) {
					session.delete(a);
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			Utility.rollbackTransaction(tx);
			throw new AnswerException(Constant.ANSWER_NOT_DELETED);
		} finally {
			Utility.closeSession(session);
		}
		return true;
	}

}
