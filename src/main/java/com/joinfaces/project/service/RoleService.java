package com.joinfaces.project.service;

import java.util.List;

import com.joinfaces.project.model.Role;

public interface RoleService {

	public List<Role> findAllRole();

	public Role findByRole(String role);
	
	public Role findById(Integer id);

}
