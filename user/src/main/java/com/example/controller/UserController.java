package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.UserDto;
import com.example.model.UserResponse;
import com.example.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// create user rest api
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

		return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);

	}

	// get All users rest API
	@GetMapping
	public UserResponse getAllUsers() {
		return userService.getAllUsers();
		
		
	}

	// get user by id -> /api/users/1
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") long id) {

		return ResponseEntity.ok(userService.getUserById(id));

	}

	//@RequestBody - body
	//@PathVariable - http url
	// update user by id rest api
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable(name = "id") long id) {

		UserDto userResponse = userService.updateUser(userDto, id);
		
		return new ResponseEntity<>(userResponse, HttpStatus.OK);

	}
	
	//delete user rest api
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(name = "id") long id) {
		
	userService.deleteUserId(id);
	return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	

}
