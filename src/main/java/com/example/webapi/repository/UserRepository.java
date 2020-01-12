package com.example.webapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.webapi.config.GenericRepository;
import com.example.webapi.model.User;

@Repository
public interface UserRepository extends GenericRepository<User> {
	Optional<User> findByEmail(String email);

	Optional<User> findByUsernameOrEmail(String username, String email);

	List<User> findByIdIn(List<Long> userIds);

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
}
