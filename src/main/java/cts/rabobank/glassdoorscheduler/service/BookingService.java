package cts.rabobank.glassdoorscheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;

@Service
public class BookingService {

	@Autowired
	private BookingRepo bookingRepo;
	
	
	public void saveBooking(Booking booking) {
		bookingRepo.save(booking);
	}
	
	public List<Booking> getAllBookings() {
		return bookingRepo.findAll();
	}
	
	
	public boolean canBookingAllowed(Booking booking) {
		return true;
	}
}
