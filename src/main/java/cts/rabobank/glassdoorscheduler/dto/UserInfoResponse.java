package cts.rabobank.glassdoorscheduler.dto;

import java.util.List;

import cts.rabobank.glassdoorscheduler.entity.UserInfo;
import cts.rabobank.glassdoorscheduler.util.CustomMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse extends CustomMessage{
   
	private List<UserInfo> userInfoList;
	
}
