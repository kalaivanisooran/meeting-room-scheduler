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
public class BookingInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Room Id should not be empty")
	private Integer roomId;

	@NotNull(message = "Employee Id should not be empty")
	private Integer usrEmpId;

	//@NotNull(message = "StartDate should not be empty")
	//@FutureOrPresent(message = "Date must not be the past")
	//Start will be empty in custom booking case
	//Non null check will be added manually for non custom booking case. 
	private LocalDate bookingStartDate;

	@NotNull(message = "Start Time should not be empty")
	private LocalTime bookingStartTime;

	@NotNull(message = "End Time should not be empty")
	private LocalTime bookingEndTime;

	@NotNull(message = "Purpose should not be empty")
	private int meetingTypeId;

	private List<LocalDate> customBookingDate;

	@NotNull(message = "bookingmode should not be empty")
	private String bookingMode;
}
