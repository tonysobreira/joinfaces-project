package com.joinfaces.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joinfaces.project.model.Role;

@Repository("roleDao")
public interface RoleDao extends JpaRepository<Role, Integer> {

	Role findByRole(String role);

}
