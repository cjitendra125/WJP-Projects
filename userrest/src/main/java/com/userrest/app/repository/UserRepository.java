package com.userrest.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userrest.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public List<User> findByName(String name);
	public List<User> findByNameLike(String pattern);
}
