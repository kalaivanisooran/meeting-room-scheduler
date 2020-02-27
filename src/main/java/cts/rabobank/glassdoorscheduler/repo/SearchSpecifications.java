package cts.rabobank.glassdoorscheduler.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.Searching;

public class SearchSpecifications {

	public static Specification<Booking> searchMeetingRooms(Searching searching) {
		return (booking, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (searching.getBookingDate() != null) {
				predicates.add(cb.and(cb.equal(booking.get("bookingDate"), searching.getBookingDate())));
			}
			if (searching.getBookingStartTime() != null) {
				predicates.add(cb.and(cb.equal(booking.get("bookingStartTime"), searching.getBookingStartTime())));
			}
			if (searching.getBookingEndTime() != null) {
				predicates.add(cb.and(cb.equal(booking.get("bookingEndTime"), searching.getBookingEndTime())));
			}
			if (searching.getRoomId() != null) {
				predicates.add(cb.and(cb.equal(booking.get("roomInfo"), searching.getRoomId())));
			}
			if (searching.getUsrEmpId() != null) {
				predicates.add(cb.and(cb.equal(booking.get("userInfo"), searching.getUsrEmpId())));
			}

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

	}
}