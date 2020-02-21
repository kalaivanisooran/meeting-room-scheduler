package cts.rabobank.glassdoorscheduler.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "BOOKING_INFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {
	
	@EmbeddedId
	private BookingIdentity bookingIdentity;
	
	@Column(name = "CREATEDON", nullable = false)
	private String createdOn;
	
	@ManyToOne(cascade=CascadeType.ALL)  
	private UserInfo userInfo;
	
	@Column(name = "PURPOSE", nullable = false)
	private String purpose;

}
