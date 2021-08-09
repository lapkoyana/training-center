package com.qcs.qualitycontrolsystem.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date dateOfReply;
	private String content;

	@ManyToOne
	private User user;
	@ManyToOne
	private Question question;
	@ManyToOne
	private Lesson lesson;

	@ManyToMany
	@JoinTable(name = "answer_user", joinColumns = @JoinColumn(name = "answer_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users = new HashSet<User>();

	public Answer() {
	}

	public Answer(Date dateOfReply, String content, User user, Question question, Lesson lesson) {
		this.dateOfReply = dateOfReply;
		this.content = content;
		this.user = user;
		this.question = question;
		this.lesson = lesson;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

}
