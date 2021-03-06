package com.ole.service;

import java.util.List;

import com.ole.exception.AnswerException;
import com.ole.model.Answer;

public interface AnswerService {
	boolean saveAnswer(Answer answer) throws AnswerException;
	List<Answer> getAllAnswer(long userId, long examId, boolean isInternalRequest ) throws AnswerException;
	boolean deleteAllAnswer(long userId, long examId ) throws AnswerException;
}
