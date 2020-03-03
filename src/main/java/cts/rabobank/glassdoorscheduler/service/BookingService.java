package cts.rabobank.glassdoorscheduler.service;

import cts.rabobank.glassdoorscheduler.exception.InvalidInputRequestException;
import cts.rabobank.glassdoorscheduler.repo.BookingIDRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.BookingInfo;
import cts.rabobank.glassdoorscheduler.entity.Searching;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;
import cts.rabobank.glassdoorscheduler.repo.SearchSpecifications;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookingService {

	@Autowired
	private BookingRepo bookingrepo;

	@Autowired
	private BookingIDRepo bookingIDRepo;

	public Booking bookRoom(Booking booking) {
		return bookingrepo.save(booking);
	}

	public void cancelMeetingRoom(Long bookingId) {
		try {
			bookingrepo.deleteById(bookingId);
		} catch (Exception e) {
			throw new InvalidInputRequestException("Invalid. Requested meeting room information is not available");
		}
	}

	public List<Booking> searchMeetingRooms(Searching searching) {
		return bookingrepo.findAll(SearchSpecifications.searchMeetingRooms(searching));
	}
	
	
	public boolean canBookingAllowed(BookingInfo b) {
		boolean canBook = true;
		Optional<Booking> booked = bookingrepo.doBookingSlotAvailable(b.getBookingDate(), b.getRoomId().longValue(), b.getBookingStartTime(), b.getBookingEndTime());
		if(booked.isPresent()) {
			canBook = false;
		}
		return canBook;
	}

}
