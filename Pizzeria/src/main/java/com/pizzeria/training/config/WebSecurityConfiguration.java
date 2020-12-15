package com.pizzeria.training.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pizzeria.training.service.CustomerService;
import com.pizzeria.training.service.JwtFilterRequest;
//
//@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomerService custService;
	
	@Autowired
	private JwtFilterRequest jwtFilterRequest;
	
	
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(custService);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http.cors().and();
		http.httpBasic().disable();
		http.authorizeRequests()
			.antMatchers("/*").permitAll()
			.antMatchers("/", "static/css", "static/js").permitAll()
			.and().formLogin();
    	
    	
        http
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/register","/auth","/")
        .permitAll()
        .anyRequest()
        .authenticated();
        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return NoOpPasswordEncoder.getInstance();
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
    	return super.authenticationManagerBean();
    }
}
