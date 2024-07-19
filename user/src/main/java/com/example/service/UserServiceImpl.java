package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.model.UserDto;
import com.example.model.UserResponse;
import com.example.repo.UserRepository;


@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {

		// convert DTO to entity
		User user = mapToEntity(userDto);
		User newUser = userRepository.save(user);

		// convert entity to DTO
		UserDto reponse = mapToDto(newUser);
		return reponse;
	}

	@Override
	public UserResponse getAllUsers() {
		
		

		List<User> listOfuser = userRepository.findAll();
		
		
		
		// Iterating elements sequestially -> just like for each loop()
		List<UserDto>content = listOfuser.stream()
				.map(user -> mapToDto(user))
				.collect(Collectors.toList());
	
		UserResponse userResponse = new UserResponse();
		userResponse.setContent(content);
		
	
		return userResponse;
	}

	// convert entity to DTO
	private UserDto mapToDto(User user) {

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAge(user.getAge());

		return userDto;

	}

	// convert DTO to entity
	private User mapToEntity(UserDto userDto) {

		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAge(userDto.getAge());

		return user;

	}

	@Override
	public UserDto getUserById(long id) {

		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("USER", "id", id));

		return mapToDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, long id) {

		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("USER", "id", id));

		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAge(userDto.getAge());

		User updatedUser = userRepository.save(user);

		return mapToDto(updatedUser);
	}

	@Override
	public void deleteUserId(long id) { // 1,2

		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("USER", "id", id));
		
		userRepository.delete(user);
		
		
	}

}
