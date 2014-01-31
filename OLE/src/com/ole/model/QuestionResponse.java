package com.ole.model;

import java.util.List;

public class QuestionResponse extends BaseResponse{
	private List<Question> questions;
	private Question question;
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
	
}
