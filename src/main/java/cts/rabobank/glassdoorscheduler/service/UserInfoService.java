package cts.rabobank.glassdoorscheduler.service;

import java.util.List;
import java.util.Optional;

import cts.rabobank.glassdoorscheduler.exception.InvalidInputRequestException;
import lombok.Lombok;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cts.rabobank.glassdoorscheduler.entity.UserInfo;
import cts.rabobank.glassdoorscheduler.repo.UserInfoRepo;
import org.springframework.data.domain.Example;

@Service
public class UserInfoService implements UserDetailsService {

	@Autowired
	private UserInfoRepo userInfoRepo;

	Logger logger = LoggerFactory.getLogger(UserInfoService.class);

	public UserInfo findUserById(Long id) {
		return Optional.ofNullable(userInfoRepo.findByUsrEmpId(id)).orElseThrow(()->new InvalidInputRequestException("Invalid. Requested user information is not available"));
	}

	public List<UserInfo> findAllUser() {
		return userInfoRepo.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String usrEmpId) throws UsernameNotFoundException {
		UserInfo user = userInfoRepo.findByUsrEmpId(Long.valueOf(usrEmpId));
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		User securedUser = new User(user.getUsername(), user.getPassword(), user.getAuthorities());
		logger.info("loadUserByUsername Invoked in Service Layer===============>" + securedUser);
		return securedUser;
	}

	public void saveUser(UserInfo user) {
		userInfoRepo.save(user);
	}

	//TODO Check and remove this line
//	public UserInfo findByUserName(String usrEmpId) {
//		return userInfoRepo.findByUsrEmpId(usrEmpId);
//	}

	public boolean isUserExist(UserInfo user) {
		boolean isUserAvail = false;
		//if (user.getUsrEmpId() != null) {
		//	return isUserAvail;
		//}

		UserInfo usrEmpId = new UserInfo();
		usrEmpId.setUsrEmpId(user.getUsrEmpId());
		
		Example<UserInfo> empExample = Example.of(usrEmpId);
		Optional<UserInfo> empId = userInfoRepo.findOne(empExample);

		if (empId.isPresent()) {
			isUserAvail = true;
		}

		return isUserAvail;
	}
}
