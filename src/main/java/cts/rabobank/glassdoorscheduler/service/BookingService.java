package cts.rabobank.glassdoorscheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;

@Service
public class BookingService {

	@Autowired
	private BookingRepo bookingrepo;

	public Booking bookRoom(Booking booking) {
		return bookingrepo.save(booking);
	}

}
