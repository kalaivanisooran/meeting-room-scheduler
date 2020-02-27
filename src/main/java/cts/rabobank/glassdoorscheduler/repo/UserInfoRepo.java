package cts.rabobank.glassdoorscheduler.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cts.rabobank.glassdoorscheduler.entity.UserInfo;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
	
	public UserInfo findByUsrEmpId(Long usrEmpId);
	
}
