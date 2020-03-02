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
	
	private String username;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Long getUsrEmpId() {
		return usrEmpId;
	}

	public void setUsrEmpId(Long usrEmpId) {
		this.usrEmpId = usrEmpId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalTime getBookingStartTime() {
		return bookingStartTime;
	}

	public void setBookingStartTime(LocalTime bookingStartTime) {
		this.bookingStartTime = bookingStartTime;
	}

	public LocalTime getBookingEndTime() {
		return bookingEndTime;
	}

	public void setBookingEndTime(LocalTime bookingEndTime) {
		this.bookingEndTime = bookingEndTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
