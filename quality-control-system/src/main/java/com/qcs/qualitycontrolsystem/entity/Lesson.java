package com.qcs.qualitycontrolsystem.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Lesson {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String topic;
	private String dateOfClass;
	private String lectureFile;
	private boolean signOfCompleteness;
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH, CascadeType.REMOVE})
	private List<Question> questions;
	
	@OneToMany(targetEntity=UserLesson.class, mappedBy="lesson")
	private List<UserLesson> userLesson;
	
	public Lesson() {
	}
	
	public Lesson(String topic, String dateOfClass, boolean signOfCompleteness) {
		this.topic = topic;
		this.dateOfClass = dateOfClass;
		this.signOfCompleteness = signOfCompleteness;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDateOfClass() {
		return dateOfClass;
	}
	public void setDateOfClass(String dateOfClass) {
		this.dateOfClass = dateOfClass;
	}
	public String getLectureFile() {
		return lectureFile;
	}
	public void setLectureFile(String lectureFile) {
		this.lectureFile = lectureFile;
	}
	public boolean isSignOfCompleteness() {
		return signOfCompleteness;
	}
	public void setSignOfCompleteness(boolean signOfCompleteness) {
		this.signOfCompleteness = signOfCompleteness;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}