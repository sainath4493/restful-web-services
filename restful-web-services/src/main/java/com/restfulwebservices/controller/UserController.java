package com.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restfulwebservices.bean.User;
import com.restfulwebservices.exception.UserNotFoundException;
import com.restfulwebservices.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> retriverAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/user/{userId}")
	public User retrieveUser(@PathVariable int userId) {

		User user = userService.findOne(userId);
		if (user == null)
			throw new UserNotFoundException("Id - " + userId);

		return user;
	}

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable int userId) {

		User user = userService.deleteUser(userId);
		if (user == null)
			throw new UserNotFoundException("Id - " + userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.saveUser(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
