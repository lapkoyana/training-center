package com.example.qualitycontrolsystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.qualitycontrolsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
