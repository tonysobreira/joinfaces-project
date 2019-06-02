package com.joinfaces.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joinfaces.project.model.Role;
import com.joinfaces.project.model.User;

@Repository
public class UserDaoImpl implements UserDaoCustom {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
    private DataSource dataSource;
	
	//https://stackoverflow.com/questions/30431035/spring-jpa-repository-dynamic-query
	//https://thoughts-on-java.org/jpa-native-queries/
	
	@Override
	public List<User> findAllUserNative() {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select u.user_id, u.active, u.email, u.name, u.last_name, u.password, ");
		sql.append(" ur.user_id as ur_user_id, ur.role_id as ur_role_id, ");
		sql.append(" r.role_id, r.role ");
		sql.append(" from users u ");
		sql.append(" left join user_role ur on ur.user_id = u.user_id ");
		sql.append(" left join roles r on r.role_id = ur.role_id ");
		
		Query q = em.createNativeQuery(sql.toString());
		
		List<Object[]> list = q.getResultList();
		
		List<User> userList = new ArrayList<User>();
		User user;
		Set<Role> roles;
		Role role;
		
		for (Object[] obj : list) {
			int id = (int) obj[0];
			boolean active = (boolean) obj[1];
			String email = (String) obj[2];
			String name = (String) obj[3];
			String lastName = (String) obj[4];
			String password = (String) obj[5];
			
			Integer urUserId = (Integer) obj[6];
			Integer urRoleId = (Integer) obj[7];
			
			Integer roleId = (Integer) obj[8];
			String roleName = (String) obj[9];
			
			user = new User();
			user.setId(id);
			user.setActive(active);
			user.setEmail(email);
			user.setName(name);
			user.setLastName(lastName);
			user.setPassword(password);
			
			boolean newUser = false;
			
			for (User u : userList) {
				
				if (u.getId().equals(urUserId)) {
					role = new Role();
					role.setId(roleId);
					role.setRole(roleName);
					
					u.getRoles().add(role);
					
					newUser = false;
				} else {
					roles = new HashSet<Role>();
					role = new Role();
					role.setId(roleId);
					role.setRole(roleName);
					roles.add(role);
					
					user.setRoles(roles);
					
					newUser = true;
				}
				
			}
			
			if (userList.isEmpty()) {
				roles = new HashSet<Role>();
				role = new Role();
				role.setId(roleId);
				role.setRole(roleName);
				roles.add(role);
				
				user.setRoles(roles);
				
				userList.add(user);
			}
			
			if (newUser) {
				userList.add(user);
			}
			
			
		}
		
		//another way
//		Object[] objArr = (Object[]) q.getSingleResult();
//		
//		for (Object obj : objArr) {
//			System.out.println(obj);
//		}
		
		return userList;
	}

	@Override
	public List<User> findAllUserHQL() {
		Query q = em.createQuery(" select u from User u ");
		List<User> list = q.getResultList();
		return list;
	}
	
	@Override
	public List<User> findAllUserDataSource() throws SQLException {
		List<User> list = new ArrayList<User>();
		User user;
		Set<Role> roles;
		Role role;
		
		Connection con = dataSource.getConnection();
		
		Statement st = con.createStatement();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select u.user_id, u.active, u.email, u.name, u.last_name, u.password, ");
		sql.append(" ur.user_id as ur_user_id, ur.role_id as ur_role_id, ");
		sql.append(" r.role_id, r.role ");
		sql.append(" from users u ");
		sql.append(" left join user_role ur on ur.user_id = u.user_id ");
		sql.append(" left join roles r on r.role_id = ur.role_id ");
		
		ResultSet result = st.executeQuery(sql.toString());
		
		while (result.next()) {
			Integer id = result.getInt("user_id");
			boolean active = result.getBoolean("active");
			String email = result.getString("email");
			String name = result.getString("name");
			String lastName = result.getString("last_name");
			String password = result.getString("password");
			
			Integer urUserId = result.getInt("ur_user_id");
			Integer urRoleId = result.getInt("ur_role_id");
			
			Integer roleId = result.getInt("role_id");
			String roleName = result.getString("role");
			
			user = new User();
			user.setId(id);
			user.setActive(active);
			user.setEmail(email);
			user.setName(name);
			user.setLastName(lastName);
			user.setPassword(password);
			
			boolean newUser = false;
			
			for (User u : list) {
				
				if (u.getId().equals(urUserId)) {
					role = new Role();
					role.setId(roleId);
					role.setRole(roleName);
					
					u.getRoles().add(role);
					
					newUser = false;
				} else {
					roles = new HashSet<Role>();
					role = new Role();
					role.setId(roleId);
					role.setRole(roleName);
					roles.add(role);
					
					user.setRoles(roles);
					
					newUser = true;
				}
				
			}
			
			if (list.isEmpty()) {
				roles = new HashSet<Role>();
				role = new Role();
				role.setId(roleId);
				role.setRole(roleName);
				roles.add(role);
				
				user.setRoles(roles);
				
				list.add(user);
			}
			
			if (newUser) {
				list.add(user);
			}
			
		}
		
		st.close();
		con.close();
		return list;
	}

}
