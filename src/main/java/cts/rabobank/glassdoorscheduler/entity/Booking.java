package cts.rabobank.glassdoorscheduler.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "BOOKING_GD")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	
	 
	
	@EmbeddedId
	private BookingId bookId;
	
	private Long userId;
	
	

}
