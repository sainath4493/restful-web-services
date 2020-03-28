package com.restfulwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.restfulwebservices.bean.User;

@Component
public class UserDaoImpl implements UserDao {

	private static List<User> users = new ArrayList<User>();
	private static int userCount = 3;
	static {
		users.add(new User(1, "a", new Date()));
		users.add(new User(2, "b", new Date()));
		users.add(new User(3, "c", new Date()));
	}

	@Override
	public List<User> findAll() {
		return users;
	}

	@Override
	public User saveUser(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;

	}

	@Override
	public User findOne(int userId) {
		for (User u : users) {
			if (u.getId() == userId) {
				return u;
			}
		}
		return null;
	}

	@Override
	public User deleteUser(int userId) {
		/*
		 * for (User u : users) { if (u.getId() == userId) { users.remove(u); return u;
		 * } }
		 */
		Iterator<User> itr = users.iterator();
		while (itr.hasNext()) {
			User u = itr.next();
			if (u.getId() == userId) {
				itr.remove();
				return u;
			}
		}
		return null;
	}
}
