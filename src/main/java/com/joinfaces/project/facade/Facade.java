package com.joinfaces.project.facade;

import java.util.List;

import com.joinfaces.project.model.Role;
import com.joinfaces.project.model.User;

public interface Facade {

	public User findUserByEmail(String email);

	public User saveUser(User user);
	
	public void deleteUser(User user);

	public List<Role> findAllRole();
	
	public Role findRoleById(Integer id);

	public Role findByRole(String role);

	public String encodePassword(CharSequence rawPassword);

	public List<User> findAllUser();

}
