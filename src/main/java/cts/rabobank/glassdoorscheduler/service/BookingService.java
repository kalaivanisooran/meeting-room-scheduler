package cts.rabobank.glassdoorscheduler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;

@Service
public class BookingService {

	@Autowired
	private BookingRepo bookingrepo;

	Logger logger = LoggerFactory.getLogger(BookingService.class);

	public Booking bookRoom(Booking booking) {
		return bookingrepo.save(booking);
	}

}
