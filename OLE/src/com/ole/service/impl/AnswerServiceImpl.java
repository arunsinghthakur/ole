package com.ole.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ole.dao.AnswerDAO;
import com.ole.exception.AnswerException;
import com.ole.model.Answer;
import com.ole.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	AnswerDAO answerDAO;
	
	@Override
	@Transactional
	public boolean saveAnswer(Answer answer) throws AnswerException {
		return answerDAO.saveAnswer(answer);
	}

	@Override
	@Transactional
	public List<Answer> getAllAnswer(long userId, long examId, boolean isInternalRequest) throws AnswerException {
		return answerDAO.getAllAnswer(userId, examId, isInternalRequest);
	}
	
	@Transactional
	@Override
	public boolean deleteAllAnswer(long userId, long examId ) throws AnswerException {
		return answerDAO.deleteAllAnswer(userId, examId);
	}

}
