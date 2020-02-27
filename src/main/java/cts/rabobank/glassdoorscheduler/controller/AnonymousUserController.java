package cts.rabobank.glassdoorscheduler.controller;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import cts.rabobank.glassdoorscheduler.entity.UserInfo;
import cts.rabobank.glassdoorscheduler.service.UserInfoService;
import cts.rabobank.glassdoorscheduler.util.CustomMessage;

@RestController
@RequestMapping("/openapi")
public class AnonymousUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(AnonymousUserController.class);

	@Autowired
	private UserInfoService userDetailService;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@PostMapping("/register")
	public ResponseEntity<CustomMessage> saveUser(@RequestBody UserInfo user, UriComponentsBuilder ucBuilder) {

		logger.debug("Creating User : {}", user);

		if (userDetailService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getFullName());
			return new ResponseEntity<>(new CustomMessage(HttpStatus.CONFLICT.value(), "Username already exist."), HttpStatus.CONFLICT);
		}

		user.setPassword(pwdEncoder.encode(user.getPassword()));
		user.setCreadtedDate(new Date());
		user.setRole("ADMIN");
		user.setStatus(true);
		userDetailService.saveUser(user);
		logger.info("User with id {} Created",user.getUsername());
		return new ResponseEntity<>(new CustomMessage(HttpStatus.OK.value(), "Successfully Registered"), HttpStatus.CREATED);
	}
}
