package br.com.pp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Define as Regras de seguranca.
 * 
 * @author George
 *
 */
@Component
@EnableWebSecurity
public class SecurityConfiguration extends  WebSecurityConfigurerAdapter{

	@Autowired
	private UserRoleService userRoleService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/pp").hasRole("ADMIN")
        .anyRequest().authenticated().and()
        .httpBasic().and()
        .csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userRoleService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
