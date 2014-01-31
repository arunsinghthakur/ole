package com.ole.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ole.dao.AnswerDAO;
import com.ole.dao.ExamDAO;
import com.ole.dao.ResultDAO;
import com.ole.exception.AnswerException;
import com.ole.exception.ExamException;
import com.ole.exception.ResultException;
import com.ole.model.Answer;
import com.ole.model.Exam;
import com.ole.model.Question;
import com.ole.model.Result;
import com.ole.model.ResultResponse;
import com.ole.model.User;
import com.ole.service.ResultService;
import com.ole.util.Utility;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	private ResultDAO resultDAO;
	
	@Autowired
	private AnswerDAO answerDAO;
	
	@Autowired
	private ExamDAO examDAO;

	@Override
	@Transactional
	public ResultResponse calculateResult(long userId, long examId, boolean isInternalRequest) throws ResultException, AnswerException, ExamException{
		ResultResponse response = new ResultResponse();
		List<Answer> answers = answerDAO.getAllAnswer(userId, examId, isInternalRequest);
		Exam exam = examDAO.getExam(examId);
		int correctQCount = 0;
		List<Question> questions = exam.getQuestion();
		Map<Long, Integer> quesCOption = new HashMap<Long, Integer>();
		
		for(Question ques : questions) {
			quesCOption.put(ques.getQuestionId(), ques.getCorrectOption());
		}
		
		for(Answer answer : answers) {
			Question q = answer.getQuestion();
			Integer cop = quesCOption.get(q.getQuestionId());
			if(cop == answer.getOptionSelect()){
				++correctQCount;
			}
		}
		
		double percentage = Utility.calculatePercentage(exam.getTotalQuestion(), correctQCount);
		String result = percentage >= exam.getPassPercentage() ? "Pass" : "Failed";
		
		Result resultBean = createResultBean(examId, userId, correctQCount, result, percentage, answers.size());
		resultDAO.saveResult(resultBean);
		
		answerDAO.deleteAllAnswer(userId, examId);
		
		resultBean.getExam().setExamName(exam.getExamName());
		resultBean.getExam().setTotalQuestion(exam.getTotalQuestion());
		response.setResult(resultBean);
		return response;
	}
	
	private Result createResultBean(long examId, long userId, long correctAnswer, String examResult, double percentage, long questionAttempted) {
		Result result = new Result();
		
		Exam exam = new Exam();
		exam.setExamId(examId);
		result.setExam(exam);
		User user = new User();
		user.setUserId(userId);
		result.setUser(user);

		result.setCorrectAnswer(correctAnswer);
		result.setExamResult(examResult);
		result.setPercentage(percentage);
		result.setQuestionAttempted(questionAttempted);
		return result;
	}
}
