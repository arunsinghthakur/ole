package com.ole.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESULT")
public class Result {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RESULT_ID")
	private Long resultId;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "EXAM_ID", nullable = false)
	private Exam exam;
	
	@Column(name = "PERCENTAGE", nullable = false)
	private Double percentage;
	
	@Column(name = "QUESTION_ATTEMPTED", nullable = false)
	private Long questionAttempted;
	
	@Column(name = "CORRECT_ANSWER", nullable = false)
	private Long correctAnswer;
	
	@Column(name = "EXAM_RESULT", nullable = false)
	private String examResult;

	public Long getResultId() {
		return resultId;
	}

	public Long getQuestionAttempted() {
		return questionAttempted;
	}

	public void setQuestionAttempted(Long questionAttempted) {
		this.questionAttempted = questionAttempted;
	}

	public Long getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Long correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getExamResult() {
		return examResult;
	}

	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}

	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	
}
