package cts.rabobank.glassdoorscheduler.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;

import org.slf4j.Logger;

import cts.rabobank.glassdoorscheduler.entity.UserInfo;
import cts.rabobank.glassdoorscheduler.service.UserInfoService;
import cts.rabobank.glassdoorscheduler.util.CustomMessage;

@RestController
@RequestMapping("/openapi")
public class AnonymousUserController {
	
	public static Logger logger = LoggerFactory.getLogger(AnonymousUserController.class);
	@Autowired
	private UserInfoService userDetailService;

	@Autowired
	private PasswordEncoder pwdEncoder;
 
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserInfo user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userDetailService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getFullName());
			return new ResponseEntity<CustomMessage>(new CustomMessage(HttpStatus.CONFLICT, "Username already exist."), HttpStatus.CONFLICT);//409
		}

		
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		user.setCreadtedDate(new Date());
		user.setRole("ADMIN");
		user.setStatus(true);
		userDetailService.saveUser(user);
		
	
		logger.info("User with id " + user.getUsername() + " Created");
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user-service/fetchuser/{id}").buildAndExpand(user.getUsrEmpId()).toUri());
		return new ResponseEntity<CustomMessage>(new CustomMessage(HttpStatus.OK, "Successfully Registered"), HttpStatus.CREATED);
	}

}
