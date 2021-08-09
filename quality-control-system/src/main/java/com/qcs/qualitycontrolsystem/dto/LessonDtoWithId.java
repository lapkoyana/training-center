package com.qcs.qualitycontrolsystem.dto;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LessonDtoWithId {
	private Long id;
	private String topic;
	private String dateOfClass;
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

	public Date getDateOfClass() throws ParseException {
	    return new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dateOfClass).getTime());
	}

	public boolean isSignOfCompleteness() {
		return signOfCompleteness;
	}

	public void setSignOfCompleteness(boolean signOfCompleteness) {
		this.signOfCompleteness = signOfCompleteness;
	}
}
