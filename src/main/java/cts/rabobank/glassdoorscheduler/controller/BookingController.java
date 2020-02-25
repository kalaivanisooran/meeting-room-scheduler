package cts.rabobank.glassdoorscheduler.controller;

import cts.rabobank.glassdoorscheduler.entity.*;
import cts.rabobank.glassdoorscheduler.service.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cts.rabobank.glassdoorscheduler.service.BookingService;
import cts.rabobank.glassdoorscheduler.service.UserInfoService;

@RestController
@RequestMapping("/bookingroom")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	RoomInfoService roomInfoService;

	@Autowired
	BookingInfo bookingInfo;

	@PostMapping(value = "/bookroom")
	public ResponseEntity<Booking> bookRoom(@RequestBody BookingInfo bookingInfo) {

		Room room = roomInfoService.findByRoomId((long) bookingInfo.getRoomId());

		BookingIdentity bookingIdentity = new BookingIdentity();
		bookingIdentity.setBookingDate(bookingInfo.getBookingDate());
		bookingIdentity.setBookingStartTime(bookingInfo.getBookingStartTime());
		bookingIdentity.setBookingEndTime(bookingInfo.getBookingEndTime());
		bookingIdentity.setRoomInfo(room);

		UserInfo userInfo = userInfoService.findUserById((long) bookingInfo.getUsrEmpId());

		Booking booking = new Booking();
		booking.setPurpose("");
		booking.setBookingIdentity(bookingIdentity);
		booking.setUserInfo(userInfo);

		bookingService.bookRoom(booking);
		//TODO do we need to return the booking details here
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}

	@GetMapping(value="cancelmeetingroom/{meetingRoomId}")
	public ResponseEntity<?> cancelMeetingRoom(@PathVariable Long meetingRoomId) {
		bookingService.cancelMeetingRoom(meetingRoomId);
		//TODO Need to change
		return new ResponseEntity<String>("meeting room cancelled successfully", HttpStatus.OK);
	}

}
