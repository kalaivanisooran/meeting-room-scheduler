package cts.rabobank.glassdoorscheduler.entity;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class Searching {
	
	private Integer roomId;
	private Integer usrEmpId;
	private LocalDate bookingDate;
	private LocalTime bookingEndTime;
	private LocalTime bookingStartTime;
	
	
	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getUsrEmpId() {
		return usrEmpId;
	}

	public void setUsrEmpId(Integer usrEmpId) {
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

}
