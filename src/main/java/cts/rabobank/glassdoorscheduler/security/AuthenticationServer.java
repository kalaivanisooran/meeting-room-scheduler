package cts.rabobank.glassdoorscheduler.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import cts.rabobank.glassdoorscheduler.service.UserInfoService;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class AuthenticationServer extends WebSecurityConfigurerAdapter{
	
	
	
	private Logger log = LoggerFactory.getLogger(AuthenticationServer.class);

	@Autowired
	private UserInfoService userInfoService;
	

	public static void main(String[] args) {
		BCryptPasswordEncoder bcpwd = new BCryptPasswordEncoder(11);
		String pwd = bcpwd.encode("1234");
		System.out.println("BCryptPassword == > " +pwd);
	}
	
	/**
	 * User to encode the passwords
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder encodePassword() {
		log.info("________________:: BCryptPasswordEncoder Bean is initizalized ");
		return new BCryptPasswordEncoder(11);
	}
	
	
	
	

	public DaoAuthenticationProvider authenticationProvider() {
		log.info("________________:: Authenticate Service is invoked ");
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(encodePassword());
		daoAuthenticationProvider.setUserDetailsService(userInfoService);
		return daoAuthenticationProvider;
	}
	
	
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		log.info("________________:: authenticationManagerBean Bean is initizalized ");
		return super.authenticationManagerBean();
	}
	
	
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("______ Auth init happended___________ ");
		auth.authenticationProvider(authenticationProvider());
	}
	 
	 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("HttpSecurity In Web Security =================> ");
				http
			         .csrf()
			         .disable()
			         .headers()
			         .frameOptions()
			         .disable()
			     .and()
			         .sessionManagement()
			         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			     .and()
		            .authorizeRequests()
		            .antMatchers("/openapi/**","/oauth/token","/h2/**").permitAll()
		            .anyRequest()
		            .authenticated();
		            
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		log.info("WebSecurity Nothing Configured =================> ");
	    web.ignoring()
		        .antMatchers(HttpMethod.OPTIONS, "/**")
		        .antMatchers("/openapi/**","/h2/**");
	}

}
