package cts.rabobank.glassdoorscheduler.controller;

import cts.rabobank.glassdoorscheduler.entity.*;
import cts.rabobank.glassdoorscheduler.service.RoomInfoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cts.rabobank.glassdoorscheduler.service.BookingService;
import cts.rabobank.glassdoorscheduler.service.UserInfoService;
import cts.rabobank.glassdoorscheduler.util.CustomMessage;

@RestController
@RequestMapping("/meetingroom")
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

		//BookingIdentity bookingIdentity = new BookingIdentity();
		Booking booking = new Booking();
		booking.setBookingDate(bookingInfo.getBookingDate());
		booking.setBookingStartTime(bookingInfo.getBookingStartTime());
		booking.setBookingEndTime(bookingInfo.getBookingEndTime());
		booking.setRoomInfo(room);

		UserInfo userInfo = userInfoService.findUserById((long) bookingInfo.getUsrEmpId());

		//Booking booking = new Booking(); 
		booking.setPurpose("");
		//booking.setBookingIdentity(bookingIdentity);
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
	
	
	@RequestMapping(value = "/searchroom", method = RequestMethod.GET)
	public ResponseEntity<?> searchRooms(@RequestBody Searching searchParam) {

		List<Booking> rooms = bookingService.searchMeetingRooms(searchParam);

		if (rooms.isEmpty()) {
			return new ResponseEntity<CustomMessage>(new CustomMessage(HttpStatus.OK, "No GlassRoom found"),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Booking>>(rooms, HttpStatus.OK);
	}
}

	


