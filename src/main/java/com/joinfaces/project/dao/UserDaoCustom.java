package com.joinfaces.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.joinfaces.project.model.User;

public interface UserDaoCustom {
	List<User> findAllUserNative();
	List<User> findAllUserHQL();
	
	public List<User> findAllUserDataSource() throws SQLException;
}
