package com.ole.service;

import java.util.List;

import com.ole.exception.ExamException;
import com.ole.exception.QuestionException;
import com.ole.model.Question;

public interface QuestionService  {
	boolean addQuestion(Question question)  throws QuestionException, ExamException;
	boolean editQuestion(Question question)  throws QuestionException;
	boolean deleteQuestion(long examId, long questionId)throws QuestionException, ExamException;
	Question getQuestion( long questionId)throws QuestionException;
	List<Question> getAllQuestion(boolean filter, long count, long examId)throws QuestionException;
}
