package com.joinfaces.project.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

@ManagedBean
@RequestScoped
public class FooterBean {

	private String time;

	@PostConstruct
	public void init() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		time = sdfDate.format(now);
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
