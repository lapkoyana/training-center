package com.qcs.qualitycontrolsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_lesson")
public class UserLesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean signOfCompleteness;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne(targetEntity = Lesson.class)
	@JoinColumn(name = "lesson_id", referencedColumnName = "id")
	private Lesson lesson;

	public UserLesson() {
	}

	public UserLesson(User user, Lesson lesson) {
		this.user = user;
		this.lesson = lesson;
	}

	public boolean isSignOfCompleteness() {
		return signOfCompleteness;
	}

	public void setSignOfCompleteness(boolean signOfCompleteness) {
		this.signOfCompleteness = signOfCompleteness;
	}
}