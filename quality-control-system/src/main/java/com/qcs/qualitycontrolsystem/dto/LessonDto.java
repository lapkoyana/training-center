package com.qcs.qualitycontrolsystem.dto;

public class LessonDto {
	private String topic;
	private String dateOfClass;
	private boolean signOfCompleteness;

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

	public boolean isSignOfCompleteness() {
		return signOfCompleteness;
	}

	public void setSignOfCompleteness(boolean signOfCompleteness) {
		this.signOfCompleteness = signOfCompleteness;
	}
}
