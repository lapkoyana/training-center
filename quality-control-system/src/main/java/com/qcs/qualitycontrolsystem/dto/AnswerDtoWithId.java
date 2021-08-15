package com.qcs.qualitycontrolsystem.dto;

import java.sql.Date;

public class AnswerDtoWithId {
	private Long id;
	private Date dateOfReply;
	private String content;
	private String question;
	private Long userId;
	private Long lessonId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateOfReply() {
		return dateOfReply;
	}

	public void setDateOfReply(Date dateOfReply) {
		this.dateOfReply = dateOfReply;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Long getUser() {
		return userId;
	}

	public void setUser(Long userId) {
		this.userId = userId;
	}

	public Long getLesson() {
		return lessonId;
	}

	public void setLesson(Long lessonId) {
		this.lessonId = lessonId;
	}
}
