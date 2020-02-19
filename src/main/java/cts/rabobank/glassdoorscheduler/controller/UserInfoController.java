package cts.rabobank.glassdoorscheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cts.rabobank.glassdoorscheduler.entity.UserInfo;
import cts.rabobank.glassdoorscheduler.service.UserInfoService;
import cts.rabobank.glassdoorscheduler.util.CustomMessage;

@RestController
@RequestMapping("/userprofile")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	
	@RequestMapping(value = "/fetchusers", method = RequestMethod.GET)
	public ResponseEntity<?> listAllUsers() {
		List<UserInfo> users = userInfoService.findAllUser();
		if (users.isEmpty()) {
			return new ResponseEntity<CustomMessage>(new CustomMessage(HttpStatus.OK, "No Users found"), HttpStatus.OK);
		}
		return new ResponseEntity<List<UserInfo>>(users, HttpStatus.OK);
	}
}
