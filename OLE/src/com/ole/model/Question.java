package com.ole.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name="QUESTION")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUESTION_ID")
	private Long questionId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="EXAM_ID", nullable = false)
	@JsonBackReference
	private Exam exam;
	
	@Column(name="QUESTION_DESCCRIPTION", columnDefinition="TEXT", nullable = false)
	private String questionDescription;
	
	@Column(name="OPTION1", columnDefinition="TEXT", nullable = false)
	private String option1;
	
	@Column(name="OPTION2", columnDefinition="TEXT", nullable = false)
	private String option2;
	
	@Column(name="OPTION3", columnDefinition="TEXT", nullable = false)
	private String option3;
	
	@Column(name="OPTION4", columnDefinition="TEXT", nullable = false)
	private String option4;
	
	@Column(name="CORRECT_OPTION", nullable = false)
	private int correctOption;

	public Question() {
	}
	
	
	public Exam getExam() {
		return exam;
	}


	public void setExam(Exam exam) {
		this.exam = exam;
	}


	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public int getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}
	
	
}
