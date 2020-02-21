package cts.rabobank.glassdoorscheduler.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class BookingId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "room_id")
	private Long roomId;

	@Column(name = "start_time")
	@Temporal(TemporalType.TIME)
	private Date startTime;

	@Column(name = "end_time")
	@Temporal(TemporalType.TIME)
	private Date endTime;

	@Column(name = "book_date")
	@Temporal(TemporalType.DATE)
	private Date bookDate;

}
