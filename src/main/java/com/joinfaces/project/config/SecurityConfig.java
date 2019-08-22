package com.joinfaces.project.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(rolesQuery)
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// require all requests to be authenticated except for the resources
		//http.authorizeRequests().antMatchers("/javax.faces.resource/**").permitAll().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/public/**").permitAll().anyRequest().anonymous();
		http.authorizeRequests().antMatchers("/secure/**").permitAll().anyRequest().authenticated();
		http.exceptionHandling().accessDeniedPage("/error/accessdenied.xhtml");
		// login
		http.formLogin().loginPage("/public/login.xhtml").permitAll().failureUrl("/public/login.xhtml?error=true").defaultSuccessUrl("/");
		// logout
		http.logout().logoutSuccessUrl("/");
		// not needed as JSF 2.2 is implicitly protected against CSRF
		http.csrf().disable();
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/javax.faces.resource/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
}
