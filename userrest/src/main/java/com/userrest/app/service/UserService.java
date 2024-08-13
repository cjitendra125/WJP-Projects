package com.userrest.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userrest.app.model.User;
import com.userrest.app.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return (User) userRepository.save(user);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public Optional<User> findById(int id) {
		return userRepository.findById(null);
	}
	
	public void remove(int id) {
		userRepository.deleteById(id);
	}
	
	public long countAll() {
		return userRepository.count();
	}
	
	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}
	public List<User> findByNameLike(String pattern) {
		return userRepository.findByNameLike(pattern);
	}
}
