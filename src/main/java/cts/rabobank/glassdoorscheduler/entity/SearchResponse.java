package cts.rabobank.glassdoorscheduler.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchResponse implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3948205299289020156L;

	private Long roomId;
	
	private String roomName;
	
	private Long usrEmpId;
	
	private LocalDate bookingDate;
	
	private LocalTime bookingStartTime;
	
	private LocalTime bookingEndTime;
	
	private Long userId;
	
	private String userName;
	
	private Long bookingId;
	
	private String purpose;
	
	
}
