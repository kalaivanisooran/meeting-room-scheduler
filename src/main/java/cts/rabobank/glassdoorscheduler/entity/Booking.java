package cts.rabobank.glassdoorscheduler.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "BOOKING_INFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Room roomInfo;

	public Room getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(Room roomInfo) {
		this.roomInfo = roomInfo;
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

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Column(name = "BOOKING_DATE", nullable = false)
	private LocalDate bookingDate;

	@Column(name = "BOOKING_STARTTIME", nullable = false)
	private LocalTime bookingStartTime;

	@Column(name = "BOOKING_ENDTIME", nullable = false)
	private LocalTime bookingEndTime;

	@CreationTimestamp
	@Column(name = "CREATEDON", nullable = false)
	private Timestamp createdOn;

    @ManyToOne(cascade=CascadeType.ALL)
	//@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private UserInfo userInfo;

	@Column(name = "PURPOSE", nullable = false)
	private String purpose;

}
