package com.qcs.qualitycontrolsystem.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.qcs.qualitycontrolsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
