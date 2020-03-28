package com.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restfulwebservices.bean.Post;
import com.restfulwebservices.bean.User;
import com.restfulwebservices.exception.UserNotFoundException;
import com.restfulwebservices.repository.PostRepository;
import com.restfulwebservices.repository.UserRepository;

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users")
	public List<User> retriverAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/user/{userId}")
	public User retrieveUser(@PathVariable int userId) {

		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent())
			throw new UserNotFoundException("Id - " + userId);

		return user.get();
	}

	@DeleteMapping("/jpa/user/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userRepository.deleteById(userId);
	}

	@PostMapping("/jpa/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/jpa/users/{userId}/posts")
	public List<Post> retriverAllPosts(@PathVariable int userId) {

		Optional<User> userOptional = userRepository.findById(userId);

		if (!userOptional.isPresent())
			throw new UserNotFoundException("Id - " + userId);

		return userOptional.get().getPosts();
	}

	@PostMapping("/jpa/user/{userId}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int userId, @RequestBody Post post) {

		Optional<User> userOptional = userRepository.findById(userId);

		if (!userOptional.isPresent())
			throw new UserNotFoundException("Id - " + userId);

		User user = userOptional.get();

		post.setUser(user);

		postRepository.save(post);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
