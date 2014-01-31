package com.ole.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ole.constant.Constant;
import com.ole.dao.DatabaseConnection;
import com.ole.dao.ExamDAO;
import com.ole.exception.ExamException;
import com.ole.model.Exam;
import com.ole.util.Utility;

@Repository
public class ExamDAOImpl extends DatabaseConnection implements ExamDAO {

	@Override
	public boolean saveExam(Exam exam) throws ExamException {
		Transaction tx = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			session.save(exam);
			tx.commit();
		} catch(HibernateException x) {
			Utility.rollbackTransaction(tx);
			throw new ExamException(Constant.EXAM_ALREADY_EXIST);
		} finally {
			Utility.closeSession(session);
		}
		return true;
	}
	
	@Override
	public boolean editExam(Exam exam) throws ExamException {
		Transaction tx = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			session.update(exam);
			tx.commit();
		} catch(HibernateException x) {
			Utility.rollbackTransaction(tx);
			throw new ExamException(Constant.EXAM_NOT_UPDATE);
		} finally {
			Utility.closeSession(session);
		}
		return true;
	}

	@Override
	public boolean deleteExam(long examId)throws ExamException {
		Transaction tx = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			Exam temp = (Exam) session.get(Exam.class, examId);
			if (null == temp) {
				throw new ExamException(Constant.NO_EXAM_FOUND);
			}
			session.delete(temp);
			tx.commit();
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			throw new ExamException(Constant.EXAM_NOT_DELETE);
		} finally {
			Utility.closeSession(session);
		}
		return true;
	}

	@Override
	public Exam getExam(long examId) throws ExamException{
		Transaction tx = null;
		Exam temp = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			temp = (Exam) session
					.createQuery("from Exam where examId= :examId")
					.setParameter("examId", examId).uniqueResult();
			tx.commit();
			if (null == temp) {
				throw new ExamException(Constant.NO_EXAM_FOUND);
			}
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			throw new ExamException(Constant.NO_EXAM_FOUND);
		} finally {
			Utility.closeSession(session);
		}
		return temp;
	}

	@Override
	public List<Exam> getAllExam(boolean filter) throws ExamException{
		Transaction tx = null;
		List<Exam> temp = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			if(filter) {
				temp = (List<Exam>) session.createQuery("from Exam where examStatus= :examStatus").setParameter("examStatus", Constant.EXAM_AVAILABLE).list();
			} else {
				temp = (List<Exam>) session.createQuery("from Exam ").list();
			}
			tx.commit();
			if (null == temp) {
				throw new ExamException(Constant.NO_EXAM_FOUND);
			} else if (temp.isEmpty()) {
				throw new ExamException(Constant.NO_EXAM_ADDED);
			}
		} catch (HibernateException e) {
			Utility.rollbackTransaction(tx);
			throw new ExamException(Constant.NO_EXAM_FOUND);
		} finally {
			Utility.closeSession(session);
		}
		return temp;
	}

}
