package cts.rabobank.glassdoorscheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.sql.Time;
import java.util.Date;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingInfo {

	@NotNull
	private int roomId;

	//TODO Added this field newly to this class. Need to chk with others on the userId or UserName
	@NotNull(message ="Employee Id should not be empty")
	private int usrEmpId;

	@Pattern(regexp = "^\\d{4}(\\-)(((0)[0-9])|((1)[0-2]))(\\-)([0-2][0-9]|(3)[0-1])$", message = "Invalid user name Format")
	private String userName;

	@Future(message = "Date must not be the past")
	private Date bookingDate;

	@NotNull(message = "Start Time should not be empty")
	private Time bookingStartTime;

	@NotNull(message = "End Time should not be empty")
	private Time bookingEndTime;

}
