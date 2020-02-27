package cts.rabobank.glassdoorscheduler.entity;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.*;

@Getter
@Setter
@Embeddable
public class BookingIdentity implements Serializable {

	@ManyToOne(cascade=CascadeType.ALL)
	private Room roomInfo;
	
	@Column(name = "BOOKING_DATE", nullable = false)
	private Date bookingDate;
	
	@Column(name = "BOOKING_STARTTIME", nullable = false)
	private Time bookingStartTime;
	
	@Column(name = "BOOKING_ENDTIME", nullable = false)
	private Time bookingEndTime;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
		result = prime * result + ((bookingEndTime == null) ? 0 : bookingEndTime.hashCode());
		result = prime * result + ((bookingStartTime == null) ? 0 : bookingStartTime.hashCode());
		result = prime * result + ((roomInfo == null) ? 0 : roomInfo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingIdentity other = (BookingIdentity) obj;
		if (bookingDate == null) {
			if (other.bookingDate != null)
				return false;
		} else if (!bookingDate.equals(other.bookingDate))
			return false;
		if (bookingEndTime == null) {
			if (other.bookingEndTime != null)
				return false;
		} else if (!bookingEndTime.equals(other.bookingEndTime))
			return false;
		if (bookingStartTime == null) {
			if (other.bookingStartTime != null)
				return false;
		} else if (!bookingStartTime.equals(other.bookingStartTime))
			return false;
		if (roomInfo == null) {
			if (other.roomInfo != null)
				return false;
		} else if (!roomInfo.equals(other.roomInfo))
			return false;
		return true;
	}

}
