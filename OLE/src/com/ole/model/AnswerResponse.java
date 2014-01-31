package com.ole.model;

import java.util.List;

public class AnswerResponse extends BaseResponse{
	List<Answer> answers;

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	
}
