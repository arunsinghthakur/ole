package com.ole.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ole.constant.Constant;
import com.ole.dao.DatabaseConnection;
import com.ole.dao.QuestionDAO;
import com.ole.exception.QuestionException;
import com.ole.model.Question;
import com.ole.util.Utility;

@Repository
public class QuestionDAOImpl extends DatabaseConnection implements QuestionDAO{

	@Override
	public boolean saveQuestion(Question question) throws QuestionException {
		Transaction tx = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			session.save(question);
			tx.commit();
		} catch(HibernateException x) {
			Utility.rollbackTransaction(tx);
			throw new QuestionException(Constant.QUESTION_NOT_ADDED);
		} finally {
			Utility.closeSession(session);
		}
		return true;
	}

	@Override
	public boolean editQuestion(Question question) throws QuestionException {
		Transaction tx = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			session.update(question);
			tx.commit();
		} catch(HibernateException x) {
			Utility.rollbackTransaction(tx);
			x.printStackTrace();
			throw new QuestionException(Constant.QUESTION_NOT_UPDATE);
		} finally {
			Utility.closeSession(session);
		}
		return true;
	}

	@Override
	public boolean deleteQuestion(long questionId) throws QuestionException {
		Transaction tx = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			Question temp = (Question) session.get(Question.class, questionId);
			if (null == temp) {
				throw new QuestionException(Constant.NO_QUESTION_FOUND);
			}
			temp.getExam().getQuestion().remove(temp);
			session.delete(temp);
			tx.commit();
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			e.printStackTrace();
			throw new QuestionException(Constant.QUESTION_NOT_DELETE);
		} finally {
			Utility.closeSession(session);
		}
		return true;
	}

	@Override
	public Question getQuestion(long questionId) throws QuestionException {
		Transaction tx = null;
		Question temp = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			temp = (Question) session
					.createQuery("from Question where questionId= :questionId")
					.setParameter("questionId", questionId).uniqueResult();
			tx.commit();
			if (null == temp) {
				throw new QuestionException(Constant.NO_QUESTION_FOUND);
			}
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			throw new QuestionException(Constant.NO_QUESTION_FOUND);
		} finally {
			Utility.closeSession(session);
		}
		return temp;
	}

	@Override
	public List<Question> getAllQuestion(boolean filter, long count, long examId) throws QuestionException {
		Transaction tx = null;
		List<Question> temp = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			if(filter) {
				temp = (List<Question>) session.createQuery("from Question where exam_Id= :examId order by rand()").setParameter("examId", examId).setMaxResults((int) count).list();
			} else {
				temp = (List<Question>) session.createQuery("from Question where exam_Id= :examId").setParameter("examId", examId).list();
			}
			tx.commit();
			if (null == temp) {
				throw new QuestionException(Constant.NO_QUESTION_FOUND);
			} else if (temp.isEmpty()) {
				throw new QuestionException(Constant.NO_QUESTION_FOUND);
			}
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			throw new QuestionException(Constant.NO_QUESTION_FOUND);
		} finally {
			Utility.closeSession(session);
		}
		return temp;
	}

}
