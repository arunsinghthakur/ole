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
@Table(name = "ANSWER")
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ANSWER_ID")
	private Long answerId;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "EXAM_ID", nullable = false)
	private Exam exam;
	
	@ManyToOne
	@JoinColumn(name = "QUESTION_ID", nullable = false)
	private Question question;
	
	@Column(name = "OPTION_SELECT", nullable = false)
	private int optionSelect;

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getOptionSelect() {
		return optionSelect;
	}

	public void setOptionSelect(int optionSelect) {
		this.optionSelect = optionSelect;
	}

	
	
}
