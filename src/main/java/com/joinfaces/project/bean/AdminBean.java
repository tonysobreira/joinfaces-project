package com.joinfaces.project.bean;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

import com.joinfaces.project.model.User;

@ManagedBean
@SessionScoped
public class AdminBean extends BaseController {
	
	public void deleteUser(User user) {
		getFacade().deleteUser(user);
	}

}
