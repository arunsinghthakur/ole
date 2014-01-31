package com.ole.dao;

import java.util.List;

import com.ole.exception.QuestionException;
import com.ole.model.Question;

public interface QuestionDAO  {
	boolean saveQuestion(Question question)  throws QuestionException;
	boolean editQuestion(Question question)  throws QuestionException;
	boolean deleteQuestion(long questionId)throws QuestionException;
	Question getQuestion( long questionId)throws QuestionException;
	List<Question> getAllQuestion(boolean filter, long count, long examId)throws QuestionException;
}
