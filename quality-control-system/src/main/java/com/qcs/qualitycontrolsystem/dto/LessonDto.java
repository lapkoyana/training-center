package com.qcs.qualitycontrolsystem.dto;

public class LessonDto {
	private Long id;
	private String topic;
	private String dateOfClass;
	private String lectureFile;
	private boolean signOfCompleteness;
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
}
