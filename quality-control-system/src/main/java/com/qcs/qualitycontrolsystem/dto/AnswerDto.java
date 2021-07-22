package com.qcs.qualitycontrolsystem.dto;

public class AnswerDto {
	private String dateOfReply;
	private String content;
	private Long questionId;
	private Long userId;
	private Long lessonId;

	public String getDateOfReply() {
		return dateOfReply;
	}

	public void setDateOfReply(String dateOfReply) {
		this.dateOfReply = dateOfReply;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}
}
