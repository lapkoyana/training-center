package com.qcs.qualitycontrolsystem.entity;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "first_name")
	private String firstName;
	
	@JoinColumn(name = "last_name")
	private String lastName;
	
	private String username;
	private String password;
	
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> role;
	
	@OneToMany
	private Set<Answer> answer = new HashSet<Answer>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserLesson> userLesson = new HashSet<>();

	public User(String username) {
		this.username = username;
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User() {
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
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public Set<UserLesson> getUserLesson() {
		return userLesson;
	}
	
	public void setUserLesson(Set<UserLesson> userLesson) {
		this.userLesson = userLesson;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
//	public boolean isLecturer() {
//		return role.contains(Role.LECTURER);
//	}
//
//	public boolean isStudent() {
//		return role.contains(Role.STUDENT);
//	}
	
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return getRole();
//	}
//

//

}
