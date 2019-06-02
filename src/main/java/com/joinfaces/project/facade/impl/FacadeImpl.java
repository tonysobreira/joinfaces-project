package com.joinfaces.project.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joinfaces.project.facade.Facade;
import com.joinfaces.project.model.Role;
import com.joinfaces.project.model.User;
import com.joinfaces.project.service.RoleService;
import com.joinfaces.project.service.UserService;

@Service("facade")
public class FacadeImpl implements Facade {

	private UserService userService;
	private RoleService roleService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public FacadeImpl(@Qualifier("userService") final UserService userService,
			@Qualifier("roleService") final RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.roleService = roleService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public User findUserByEmail(String email) {
		return userService.findUserByEmail(email);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public User saveUser(User user) {
		return userService.saveUser(user);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public void deleteUser(User user) {
		userService.deleteUser(user);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public List<Role> findAllRole() {
		return roleService.findAllRole();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public Role findByRole(String role) {
		return roleService.findByRole(role);
	}

	@Override
	public String encodePassword(CharSequence rawPassword) {
		return bCryptPasswordEncoder.encode(rawPassword);
	}

	@Override
	public List<User> findAllUser() {
		return userService.findAllUser();
	}

	@Override
	public Role findRoleById(Integer id) {
		return roleService.findById(id);
	}

}
