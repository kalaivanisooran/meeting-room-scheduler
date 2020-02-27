package cts.rabobank.glassdoorscheduler.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cts.rabobank.glassdoorscheduler.entity.UserInfo;
import cts.rabobank.glassdoorscheduler.service.UserInfoService;
import cts.rabobank.glassdoorscheduler.util.CustomMessage;

@RestController
@RequestMapping("/userprofile")
public class UserInfoController {

	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	private UserInfoService userInfoService;

	@GetMapping(value = "/fetchusers")
	public ResponseEntity<?> listAllUsers() {
		List<UserInfo> users = userInfoService.findAllUser();
		if (users.isEmpty()) {
			return new ResponseEntity<CustomMessage>(new CustomMessage(HttpStatus.OK.value(), "No Users found"), HttpStatus.OK);
		}
		return new ResponseEntity<List<UserInfo>>(users, HttpStatus.OK);
	}

	@GetMapping(value = "/fetchuser/{userid}")
	public ResponseEntity<?> getUser(@PathVariable("userid") String userid) {

		logger.debug("Fetching User with userid {}", userid);
		UserInfo user = userInfoService.findUserById(Long.valueOf(userid));
		if (user == null) {
			logger.error("User with userid {} not found.", userid);
			return new ResponseEntity<CustomMessage>(
					new CustomMessage(HttpStatus.NOT_ACCEPTABLE.value(), "User " + userid + " not found"),
					HttpStatus.NOT_ACCEPTABLE);// 417
		}
		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
		
	}
}
