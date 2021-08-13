package com.qcs.qualitycontrolsystem.dto;

public class UserLessonDto {
	private long userId;
	private long lessonId;
	private boolean signOfCompleteness;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getLessonId() {
		return lessonId;
	}
	public void setLessonId(long lessonId) {
		this.lessonId = lessonId;
	}
	public boolean isSignOfCompleteness() {
		return signOfCompleteness;
	}
	public void setSignOfCompleteness(boolean signOfCompleteness) {
		this.signOfCompleteness = signOfCompleteness;
	}
	
}
