package com.ole.model;


public class History extends BaseResponse{
	private String examName;
	private long totalQuestion;
	private long questionAttempted;
	private long correctAnswer;
	private double percentage;
	private String examResult;
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public long getTotalQuestion() {
		return totalQuestion;
	}
	public void setTotalQuestion(long totalQuestion) {
		this.totalQuestion = totalQuestion;
	}
	public long getQuestionAttempted() {
		return questionAttempted;
	}
	public void setQuestionAttempted(long questionAttempted) {
		this.questionAttempted = questionAttempted;
	}
	public long getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(long correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public String getExamResult() {
		return examResult;
	}
	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}
	
	
	
}
