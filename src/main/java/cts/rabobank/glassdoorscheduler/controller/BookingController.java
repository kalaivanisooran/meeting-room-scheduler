package cts.rabobank.glassdoorscheduler.controller;

import cts.rabobank.glassdoorscheduler.entity.*;
import cts.rabobank.glassdoorscheduler.service.RoomInfoService;
import cts.rabobank.glassdoorscheduler.util.BookingValidator;
import cts.rabobank.glassdoorscheduler.util.CustomMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import cts.rabobank.glassdoorscheduler.service.BookingService;
import cts.rabobank.glassdoorscheduler.service.UserInfoService;
import javax.validation.Valid;

@RestController
@RequestMapping("/bookingroom")
public class BookingController extends BookingValidator {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private BookingValidator bookingValidator;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	RoomInfoService roomInfoService;

	@Autowired
	BookingInfo bookingInfo;

	@PostMapping(value = "/bookroom",consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomMessage> bookRoom(@Valid @RequestBody BookingInfo bookingInfo, Errors errors) {

		bookingValidator.chkBookingRoomInputField(bookingInfo,errors);

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
		return new ResponseEntity<>(new CustomMessage(HttpStatus.OK.value(),"Meeting room booked successfully"), HttpStatus.OK);
	}

	@GetMapping(value="cancelmeetingroom/{meetingRoomId}")
	public ResponseEntity<CustomMessage> cancelMeetingRoom(@PathVariable Long meetingRoomId) {
		bookingService.cancelMeetingRoom(meetingRoomId);
		//TODO Need to handle exception scenario
		return new ResponseEntity<>(new CustomMessage(HttpStatus.OK.value(),"Meeting room cancelled successfully"), HttpStatus.OK);
	}

}
