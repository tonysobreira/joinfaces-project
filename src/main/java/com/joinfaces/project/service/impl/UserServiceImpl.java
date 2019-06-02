package com.joinfaces.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinfaces.project.model.User;
import com.joinfaces.project.dao.UserDao;
import com.joinfaces.project.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDao userRepository) {
		this.userDao = userRepository;
	}

	public User findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public User saveUser(User user) {
		return userDao.save(user);
	}
	
	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public List<User> findAllUser() {
		return userDao.findAll();
	}

}