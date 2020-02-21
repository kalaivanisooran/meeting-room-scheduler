package cts.rabobank.glassdoorscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.BookingInfo;
import cts.rabobank.glassdoorscheduler.service.BookingService;
import cts.rabobank.glassdoorscheduler.service.UserInfoService;

@RestController
@RequestMapping("/bookingroom")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/bookroom", method = RequestMethod.POST) 
	public ResponseEntity<Booking> bookRoom(@ModelAttribute("bookingJSON") BookingInfo bookingInfoJSON) {	

		Booking booking = new Booking();	
		//Booking booking = bookingService.bookRoom(bookingInfoJSON);		
		
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}
}
