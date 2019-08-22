package com.joinfaces.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinfaces.project.dao.RoleDao;
import com.joinfaces.project.model.Role;
import com.joinfaces.project.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(RoleDao roleRepository) {
		this.roleDao = roleRepository;
	}

	@Override
	public List<Role> findAllRole() {
		return roleDao.findAll();
	}

	public Role findByRole(String role) {
		return roleDao.findByRole(role);
	}

	@Override
	public Role findById(Integer id) {
		Role role = null;
		
		Optional<Role> optionalRole = roleDao.findById(id);
		
		if (optionalRole.isPresent()) {
			role = optionalRole.get();
		}
		
		return role;
	}

}
