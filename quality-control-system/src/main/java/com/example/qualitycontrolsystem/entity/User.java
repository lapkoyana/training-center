package com.example.qualitycontrolsystem.entity;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	private String username;
	private String password;
	
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> role;
	
	private boolean active;
	
	@ManyToMany
	@JoinTable(name = "answer_user",
			   joinColumns = @JoinColumn(name = "user_id"),
			   inverseJoinColumns = @JoinColumn(name = "answer_id"))
	private Set<Answer> answer = new HashSet<Answer>();
	
	@OneToMany(targetEntity=UserLesson.class, mappedBy="user")
	private List<UserLesson> userLesson;
	
	public User(String username) {
		this.username = username;
	}
	
	public User() {
	}

	public boolean isLecturer() {
		return role.contains(Role.LECTURER);
	}
	public boolean isStudent() {
		return role.contains(Role.STUDENT);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<Role> getRole() {
		return role;
	}
	public void setRole(Set<Role> role) {
		this.role = role;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(Set<Answer> answer) {
		this.answer = answer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRole();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive();
	}
	
}
