package com.joinfaces.project.bean;

import org.springframework.beans.factory.annotation.Autowired;

import com.joinfaces.project.facade.Facade;

public abstract class BaseController {

	@Autowired
	private Facade facade;

	public Facade getFacade() {
		return facade;
	}

}
