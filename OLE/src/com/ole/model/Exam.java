package com.ole.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;



@Entity
@Table( name = "EXAM")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Exam {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EXAM_ID")
	private Long examId;
	
	@Column(name="EXAM_NAME", unique=true)
	private String examName;
	
	@Column(name="TOTAL_QUESTION", nullable = false)
	private Long totalQuestion;
	
	@Column(name="EXAM_TIME", nullable = false)
	private Long examTime;
	
	@Column(name="EXAM_DESCRIPTION")
	private String examDescription;
	
	@Column(name="EXAM_STATUS")
	private String examStatus = "UNAVAILABLE";
	
	@Column(name="PASS_PERCENTAGE", nullable = false)
	private Double passPercentage;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "exam", fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference
	private List<Question> question;
	
	public Exam() {
	}
	
	public Long getExamId() {
		return examId;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public Double getPassPercentage() {
		return passPercentage;
	}

	public void setPassPercentage(Double passPercentage) {
		this.passPercentage = passPercentage;
	}

	public String getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(String examStatus) {
		this.examStatus = examStatus;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Long getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(Long totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public Long getExamTime() {
		return examTime;
	}

	public void setExamTime(Long examTime) {
		this.examTime = examTime;
	}

	public String getExamDescription() {
		return examDescription;
	}

	public void setExamDescription(String examDescription) {
		this.examDescription = examDescription;
	}
	
	
}
