package com.example.service;

import com.example.model.UserDto;
import com.example.model.UserResponse;

public interface UserService {

	UserDto createUser(UserDto userDto);
	
	UserResponse getAllUsers();
	
	UserDto getUserById(long id);
	
	UserDto updateUser(UserDto userDto,long id);
	
    void deleteUserId(long id);
}
