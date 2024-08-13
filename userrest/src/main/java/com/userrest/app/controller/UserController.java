package com.userrest.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.userrest.app.constants.ResponseKey;
import com.userrest.app.constants.ResponseMessage;
import com.userrest.app.model.User;
import com.userrest.app.service.UserService;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
//	@GetMapping("/")
//	public List<User> findAll(){
//		return userService.findAll();
//	}
//	
//	@PostMapping("/")
//	public User save(@RequestBody User user) {
//		User savedUser=userService.save(user);
//		return savedUser;
//	}
	
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		HashMap<Object,Object> data=new HashMap<>();
		try {
			List<User> userList=userService.findAll();
			if(userList.isEmpty()) {
				data.put(ResponseKey.MESSAGE,ResponseMessage.NO_USER_FOUND);
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}else {
				data.put(ResponseKey.COUNT,userService.countAll());
				data.put(ResponseKey.USERS, userList);
				return new ResponseEntity<>(data,HttpStatus.OK);
				}
		}catch(Exception e) {
			data.put(ResponseKey.MESSAGE,ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> save(@RequestBody User user) {
		HashMap<Object,Object> data=new HashMap<>();
		try {
			User savedUser=userService.save(user);
			return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
		}catch(Exception e) {
			data.put(ResponseKey.MESSAGE,ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable int id){
		HashMap<Object,Object> data=new HashMap<>();
		try {
			Optional<User> userOptional=userService.findById(id);
			if(userOptional.isPresent()) {
				User user=userOptional.get();
				return new ResponseEntity<>(user,HttpStatus.OK);
			}else {
				data.put(ResponseKey.MESSAGE, ResponseMessage.NO_USER_FOUND_BY_ID);
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			data.put(ResponseKey.MESSAGE, ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remove(@PathVariable int id) {
		HashMap<Object,Object> data=new HashMap<>();
		try {
			Optional<User> userOptional=userService.findById(id);
			if(userOptional.isPresent()) {
				userService.remove(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				data.put(ResponseKey.MESSAGE, ResponseMessage.NO_USER_FOUND_BY_ID);
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			data.put(ResponseKey.MESSAGE, ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/by-name/{name}")
	public ResponseEntity<Object> findByName(@PathVariable String name){
		HashMap<Object,Object> data=new HashMap<>();
		try {
			List<User> userList=userService.findByName(name);
			return new ResponseEntity<>(userList,HttpStatus.OK);
		}catch(Exception e) {
			data.put(ResponseKey.MESSAGE, ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/name-by-like/{pattern}")
	public ResponseEntity<Object> findByNameLike(@PathVariable String pattern){
		HashMap<Object,Object> data=new HashMap<>();
		try {
			List<User> userList=userService.findByNameLike(pattern);
			if(userList.isEmpty()) {
				data.put(ResponseKey.MESSAGE, ResponseMessage.NO_USER_FOUND);
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
			}else {
				data.put(ResponseKey.USERS, userList);
                return new ResponseEntity<>(data, HttpStatus.OK);
			}
		}catch(Exception e) {
			data.put(ResponseKey.MESSAGE, ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
