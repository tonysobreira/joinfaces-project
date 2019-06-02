package com.joinfaces.project.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.joinfaces.project.dao.UserDaoImpl;
import com.joinfaces.project.model.Role;
import com.joinfaces.project.model.User;

//@Named
@ManagedBean
@SessionScoped
public class HomeBean extends BaseController {

	private String firstName = "";
	private String lastName = "";
	
	@Autowired
	UserDaoImpl udao;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<User> getUserList() {
		List<User> userList = getFacade().findAllUser();
		
		User user = userList.get(0);
		
		for (Role role : user.getRoles()) {
			System.out.println(role.getRole());
		}
		
		return userList;
	}
	
	public List<String> getUserRoles(User user) {
		List<String> rolesString = new ArrayList<String>();
		
		for (Role role : user.getRoles()) {
			rolesString.add(role.getRole());
		}
		
		return rolesString;
	}

	public String showGreeting() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		authentication.getCredentials();
		return "Hello " + authentication.getName() + "!";
	}
	
	public String back() {
		return "login";
	}
	
	public String getLoggedUser() {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
	}
	
	public List<User> testDataSource() throws Exception {
		List<User> list = udao.findAllUserDataSource();
		
		for (User user : list) {
			System.out.println(user);
		}
		
		return list;
	}
	
	public List<User> testEntityManagerHQL() {
		List<User> list = udao.findAllUserHQL();
		
		for (User user : list) {
			System.out.println(user);
		}
		
		return list;
	}
	
	public List<User> testEntityManagerNative() {
		List<User> list = udao.findAllUserNative();
		
		for (User user : list) {
			System.out.println(user);
		}
		
		return list;
	}
	
}
