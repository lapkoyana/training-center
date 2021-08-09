package com.qcs.qualitycontrolsystem.dto;

import java.sql.Date;

public class AnswerDto {
	private Date dateOfReply;
	private String content;

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
}
