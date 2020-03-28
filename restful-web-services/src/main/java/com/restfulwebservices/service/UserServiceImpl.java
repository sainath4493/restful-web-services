package com.restfulwebservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfulwebservices.bean.User;
import com.restfulwebservices.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findAll() {

		return userDao.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	@Override
	public User findOne(int userId) {
		return userDao.findOne(userId);
	}

	@Override
	public User deleteUser(int userId) {

		return userDao.deleteUser(userId);
	}

}
