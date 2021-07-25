package com.qcs.qualitycontrolsystem.dto;

public class AnswerDtoWithId {
	private Long id;
	private String dateOfReply;
	private String content;
	private String question;
	private String user;
	private String lesson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}
}
