package cts.rabobank.glassdoorscheduler.entity;

import java.util.Date;

public class BookingInfo {
	
	private String roomName;
	private String userName;
	private Date bookingDate;
	private Date bookingStartTime;
	private Date bookingEndTime;
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Date getBookingStartTime() {
		return bookingStartTime;
	}
	public void setBookingStartTime(Date bookingStartTime) {
		this.bookingStartTime = bookingStartTime;
	}
	public Date getBookingEndTime() {
		return bookingEndTime;
	}
	public void setBookingEndTime(Date bookingEndTime) {
		this.bookingEndTime = bookingEndTime;
	}

}
