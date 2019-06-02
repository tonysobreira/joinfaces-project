package com.joinfaces.project.bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import com.joinfaces.project.model.Role;
import com.joinfaces.project.model.User;

@ManagedBean
@SessionScoped
public class RegisterBean extends BaseController {

	private User user;
	private String email;
	private String password1;
	private String password2;
	
	private String name;
	private String lastName;
	private List<Role> roleList;
	private List<Role> selectedRoles;

	@PostConstruct
	public void init() {
		user = new User();
	}

	public String register() {
		
		user.setEmail(this.email);
		
		String encryptedPassword = getFacade().encodePassword(this.password1);
		user.setPassword(encryptedPassword);
		
		user.setActive(Boolean.TRUE);
		
		user.setName(this.name);
		user.setLastName(this.lastName);

//		String role = "ROLE_ADMIN";
//		Role userRole = getFacade().findByRole(role);
//		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		
		Set<Role> roleSet = new HashSet<Role>();
		Role role;
		
		for (Role r : this.selectedRoles) {
			role = getFacade().findRoleById(r.getId());
			roleSet.add(role);
		}
		
		user.setRoles(roleSet);

		getFacade().saveUser(user);

		return "startpage?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public List<Role> getRoleList() {
		
		if (roleList == null) {
			roleList = getFacade().findAllRole();
		}
		
		return roleList;
	}

	public List<Role> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<Role> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	


}
