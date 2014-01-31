package com.ole.service;

import java.util.List;

import com.ole.exception.ExamException;
import com.ole.model.Exam;

public interface ExamService  {
	boolean addExam(Exam exam)  throws ExamException;
	boolean editExam(Exam exam)  throws ExamException;
	boolean deleteExam(long examId)throws ExamException;
	Exam getExam( long examId)throws ExamException;
	List<Exam> getAllExam(boolean filter)throws ExamException;
}
