package cts.rabobank.glassdoorscheduler.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message ="Room Id should not be empty")
	private Integer roomId;
  
	//TODO Added this field newly to this class. Need to chk with others on the userId or UserName
	@NotNull(message ="Employee Id should not be empty")
	private int usrEmpId;

	private String userName; 

	@FutureOrPresent(message = "Date must not be the past")
	private LocalDate bookingDate;

	@NotNull(message = "Start Time should not be empty")
	private LocalTime bookingStartTime;

	@NotNull(message = "End Time should not be empty")
	private LocalTime bookingEndTime;
	
	private String purpose;
}
 