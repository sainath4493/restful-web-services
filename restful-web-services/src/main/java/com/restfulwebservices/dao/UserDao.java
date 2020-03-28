package com.restfulwebservices.dao;

import java.util.List;

import com.restfulwebservices.bean.User;

public interface UserDao {

	public List<User> findAll();

	public User saveUser(User user);

	public User findOne(int userId);

	public User deleteUser(int userId);
}
