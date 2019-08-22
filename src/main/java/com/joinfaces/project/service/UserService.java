package com.joinfaces.project.service;

import java.util.List;

import com.joinfaces.project.model.User;

public interface UserService {

	public User findUserByEmail(String email);

	public User saveUser(User user);
	
	public void deleteUser(User user);

	public List<User> findAllUser();

}