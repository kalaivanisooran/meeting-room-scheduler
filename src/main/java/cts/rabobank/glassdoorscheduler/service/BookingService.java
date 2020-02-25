package cts.rabobank.glassdoorscheduler.service;

import cts.rabobank.glassdoorscheduler.repo.BookingIDRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;

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

	public void cancelMeetingRoom(Long bookingId){
		bookingrepo.deleteByBookingId(bookingId);
	}
}
