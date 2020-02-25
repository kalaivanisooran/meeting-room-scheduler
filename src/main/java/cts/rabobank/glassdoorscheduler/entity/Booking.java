package cts.rabobank.glassdoorscheduler.entity;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "BOOKING_INFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@IdClass(BookingId.class)
public class Booking extends BookingId implements Serializable {

	@EmbeddedId
	private BookingIdentity bookingIdentity;

	@CreationTimestamp
	@Column(name = "CREATEDON", nullable = false)
	private Timestamp createdOn;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private UserInfo userInfo;
	
	@Column(name = "PURPOSE", nullable = false)
	private String purpose;

}
