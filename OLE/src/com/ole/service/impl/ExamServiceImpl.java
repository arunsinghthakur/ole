package com.ole.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ole.constant.Constant;
import com.ole.dao.ExamDAO;
import com.ole.exception.ExamException;
import com.ole.model.Exam;
import com.ole.service.ExamService;
import com.ole.util.Utility;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamDAO examDAO;

	@Override
	@Transactional
	public boolean editExam(Exam exam) throws ExamException {
		if (Utility.isEmpty(exam.getExamName())) {
			throw new ExamException(Constant.EXAM_REQUIRED);
		} else if (exam.getExamTime() == 0) {
			throw new ExamException(Constant.EXAM_TIME_REQUIRED);
		} else if (exam.getTotalQuestion() == 0) {
			throw new ExamException(Constant.EXAM_QUESTION_REQUIRED);
		} else if (exam.getPassPercentage() == null || exam.getPassPercentage() <= 0 || exam.getPassPercentage() > 100 ) {
			throw new ExamException(Constant.EXAM_PASS_PERCENTAGE_REQUIRED);
		}

		boolean result = false;
		result = examDAO.editExam(exam);
		Exam temp = examDAO.getExam(exam.getExamId());

		if (temp.getTotalQuestion() <= temp.getQuestion().size() && temp.getExamStatus().equalsIgnoreCase(Constant.EXAM_UNAVAILABLE)) {
			exam.setExamStatus(Constant.EXAM_AVAILABLE);
			result = examDAO.editExam(exam);
		} else if (temp.getTotalQuestion() > temp.getQuestion().size() && temp.getExamStatus().equalsIgnoreCase(Constant.EXAM_AVAILABLE)) {
			exam.setExamStatus(Constant.EXAM_UNAVAILABLE);
			result = examDAO.editExam(exam);
		}
		return result;
	}

	@Override
	@Transactional
	public boolean addExam(Exam exam) throws ExamException {
		if (Utility.isEmpty(exam.getExamName())) {
			throw new ExamException(Constant.EXAM_REQUIRED);
		} else if (exam.getTotalQuestion() == null || exam.getTotalQuestion() == 0) {
			throw new ExamException(Constant.EXAM_QUESTION_REQUIRED);
		} else if (exam.getExamTime() == null || exam.getExamTime() == 0) {
			throw new ExamException(Constant.EXAM_TIME_REQUIRED);
		} else if (exam.getPassPercentage() == null || exam.getPassPercentage() <= 0 || exam.getPassPercentage() > 100 ) {
			throw new ExamException(Constant.EXAM_PASS_PERCENTAGE_REQUIRED);
		}
		return examDAO.saveExam(exam);
	}

	@Override
	@Transactional
	public boolean deleteExam(long examId) throws ExamException {
		return examDAO.deleteExam(examId);
	}

	@Override
	@Transactional
	public Exam getExam(long examId) throws ExamException {
		return examDAO.getExam(examId);
	}

	@Override
	@Transactional
	public List<Exam> getAllExam(boolean filter) throws ExamException {
		return examDAO.getAllExam(filter);
	}

}
