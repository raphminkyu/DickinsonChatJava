//https://developer.okta.com/blog/2019/05/15/spring-boot-login-options
package com.DickinsonChatJava.config;


import org.springframework.context.annotation.Configuration;  
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;  
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;  
  

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {  
      
	@Override  
	public void configure(HttpSecurity http) throws Exception {  
	    http  
	        .authorizeRequests()  
	        .anyRequest().authenticated()  
	        .and()  
	        .formLogin()  
	        //.and()  
	        //.httpBasic();
	        .loginPage("/login")
	        .permitAll();
	}

      
    @Override  
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  
        auth.inMemoryAuthentication()  
            .withUser("user")  
            .password("{noop}pass") // Spring Security 5 requires specifying the password storage format  
            .roles("USER");  
    }  
      
}