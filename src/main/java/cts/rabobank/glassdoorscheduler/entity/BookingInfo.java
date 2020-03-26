package cts.rabobank.glassdoorscheduler.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull(message ="Room Id should not be empty")
	private Integer roomId;

	@NotNull(message ="Employee Id should not be empty")
	private int usrEmpId;

	//@NotNull(message ="StartDate should not be empty")
	//@FutureOrPresent(message = "Date must not be the past")
	//private LocalDate bookingStartDate;

	@NotNull(message = "Start Time should not be empty")
	private LocalTime bookingStartTime;

	@NotNull(message = "End Time should not be empty")
	private LocalTime bookingEndTime;

	//@NotNull(message = "Purpose should not be empty")
	//private String purpose;
	
	private int meetingTypeId;

	//@NotNull(message = "Mode should not be empty")
	//private String mode;

	private List<LocalDate> customBookingDate;
	
	@NotNull(message = "bookingmode should not be empty")
	private String bookingMode;
}
 