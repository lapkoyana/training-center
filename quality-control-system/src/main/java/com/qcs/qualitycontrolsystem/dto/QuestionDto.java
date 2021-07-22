package com.qcs.qualitycontrolsystem.dto;

public class QuestionDto {
	private String content;
	private Long lessonId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}
}
