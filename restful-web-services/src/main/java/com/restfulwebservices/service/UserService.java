package com.restfulwebservices.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restfulwebservices.bean.User;

@Service
public interface UserService {

	public List<User> findAll();

	public User saveUser(User user);

	public User findOne(int userId);

	public User deleteUser(int userId);
}
