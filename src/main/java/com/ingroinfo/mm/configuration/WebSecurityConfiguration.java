package com.ingroinfo.mm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.ingroinfo.mm.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	public AuthenticationManagerBuilder authenticationManager() {
		return authenticationManager().authenticationProvider(authenticationProvider());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				// .antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/login", "/admin", "/register/company", "/access-denied",
						"/server-error", "/get/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/home", true).failureUrl("/login?error").and().logout().logoutUrl("/logout")
				.deleteCookies("JSESSIONID").and().exceptionHandling().accessDeniedPage("/access-denied");
		http.headers().frameOptions().sameOrigin();
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/css/**", "/images/**", "/js/**");
	}

	/*
	 * @Bean public RoleHierarchy roleHierarchy() {
	 * 
	 * RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl(); String hierarchy =
	 * "ROLE_ADMIN > ROLE_COMAPNY \n ROLE_COMAPNY > ROLE_BRANCH \n ROLE_BRANCH > ROLE_USER"
	 * ; roleHierarchy.setHierarchy(hierarchy); return roleHierarchy; }
	 * 
	 * @Bean public DefaultWebSecurityExpressionHandler
	 * webSecurityExpressionHandler() { DefaultWebSecurityExpressionHandler
	 * expressionHandler = new DefaultWebSecurityExpressionHandler();
	 * expressionHandler.setRoleHierarchy(roleHierarchy()); return
	 * expressionHandler; }
	 */
	
}
