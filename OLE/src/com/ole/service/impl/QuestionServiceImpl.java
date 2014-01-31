package com.ole.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ole.constant.Constant;
import com.ole.dao.ExamDAO;
import com.ole.dao.QuestionDAO;
import com.ole.exception.ExamException;
import com.ole.exception.QuestionException;
import com.ole.model.Exam;
import com.ole.model.Question;
import com.ole.service.QuestionService;
import com.ole.util.Utility;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDAO;
	
	@Autowired
	private ExamDAO examDAO;
	
	@Override
	@Transactional
	public boolean addQuestion(Question question) throws QuestionException, ExamException {
		if(Utility.isEmpty(question.getQuestionDescription())) {
			throw new QuestionException(Constant.QUESTION_DESC_REQUIRED);
		} else if(Utility.isEmpty(question.getOption1()) || Utility.isEmpty(question.getOption2()) || Utility.isEmpty(question.getOption3()) || Utility.isEmpty(question.getOption4())) {
			throw new QuestionException(Constant.OPTIONS_REQUIRED);
		} 
		
		boolean result = false;
		result = questionDAO.saveQuestion(question);
		Exam exam = examDAO.getExam(question.getExam().getExamId());
		
		if(exam.getTotalQuestion() <= exam.getQuestion().size() && exam.getExamStatus().equalsIgnoreCase(Constant.EXAM_UNAVAILABLE)) {
			exam.setExamStatus(Constant.EXAM_AVAILABLE);
			System.out.println(exam.getExamId());
			result = examDAO.editExam(exam);
		} else if(exam.getTotalQuestion() > exam.getQuestion().size() && exam.getExamStatus().equalsIgnoreCase(Constant.EXAM_AVAILABLE)) {
			exam.setExamStatus(Constant.EXAM_UNAVAILABLE);
			result = examDAO.editExam(exam);
		}
		
		return result;
	}

	@Override
	@Transactional
	public boolean editQuestion(Question question) throws QuestionException {
		if(Utility.isEmpty(question.getQuestionDescription())) {
			throw new QuestionException(Constant.QUESTION_DESC_REQUIRED);
		} else if(Utility.isEmpty(question.getOption1()) || Utility.isEmpty(question.getOption2()) || Utility.isEmpty(question.getOption3()) || Utility.isEmpty(question.getOption4())) {
			throw new QuestionException(Constant.OPTIONS_REQUIRED);
		} 
		return questionDAO.editQuestion(question);
	}

	@Override
	@Transactional
	public boolean deleteQuestion(long examId, long questionId)throws QuestionException, ExamException {
		
		boolean result = false;
		result = questionDAO.deleteQuestion(questionId);
		Exam exam = examDAO.getExam(examId);
		
		if(exam.getTotalQuestion() > exam.getQuestion().size() && exam.getExamStatus().equalsIgnoreCase(Constant.EXAM_AVAILABLE)) {
			exam.setExamStatus(Constant.EXAM_UNAVAILABLE);
			result = examDAO.editExam(exam);
		}
		return result ;
	}

	@Override
	@Transactional
	public Question getQuestion(long questionId) throws QuestionException {
		return questionDAO.getQuestion(questionId);
	}

	@Override
	@Transactional
	public List<Question> getAllQuestion(boolean filter, long count, long examId) throws QuestionException {
		return questionDAO.getAllQuestion(filter, count, examId);
	}

	
}
