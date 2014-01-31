package com.ole.model;

import java.util.List;

public class ExamResponse extends BaseResponse {
	private List<Exam> exams;
	private Exam exam;

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	
}
