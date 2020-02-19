package cts.rabobank.glassdoorscheduler.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import cts.rabobank.glassdoorscheduler.exception.CustomAccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter{

	
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	 http
	         .authorizeRequests()
	         .antMatchers("/admin/**").access("hasRole('ADMIN')")
	         .anyRequest()
	         .authenticated()
	         .and()
	         .exceptionHandling()
	         .accessDeniedHandler(accessDeniedHandler());
    }
    
    
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

}
