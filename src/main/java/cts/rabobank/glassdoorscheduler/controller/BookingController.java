package cts.rabobank.glassdoorscheduler.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.UserInfo;
import cts.rabobank.glassdoorscheduler.service.BookingService;
import cts.rabobank.glassdoorscheduler.util.CustomMessage;

@RestController
@RequestMapping("/booking")
public class BookingController {

	public static Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService bookingService;

	@RequestMapping(value = "/book", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveUser(@RequestBody Booking booking, UriComponentsBuilder ucBuilder) {
		logger.info("Creating booking : {}", booking);

		if (!bookingService.canBookingAllowed(booking)) {
			logger.error("Unable to Book");
			return new ResponseEntity<CustomMessage>(new CustomMessage(HttpStatus.CONFLICT, "Booking Conflict"),
					HttpStatus.CONFLICT);// 409
		}
		bookingService.saveBooking(booking);
		return new ResponseEntity<CustomMessage>(new CustomMessage(HttpStatus.OK, "Successfully Booked"),
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/fetchbookings", method = RequestMethod.GET)
	public ResponseEntity<?> listAllBookings() {
		List<Booking> users = bookingService.getAllBookings();
		if (users.isEmpty()) {
			return new ResponseEntity<CustomMessage>(new CustomMessage(HttpStatus.OK, "No Users found"), HttpStatus.OK);
		}
		return new ResponseEntity<List<Booking>>(users, HttpStatus.OK);
	}
		

}
