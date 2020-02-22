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
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.Temporal;

import java.sql.Timestamp;
import java.util.Date;

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

	@CreationTimestamp
	@Column(name = "CREATEDON", nullable = false)
	private Timestamp createdOn;
	
	@ManyToOne(cascade=CascadeType.ALL)  
	private UserInfo userInfo;
	
	@Column(name = "PURPOSE", nullable = false)
	private String purpose;

}
