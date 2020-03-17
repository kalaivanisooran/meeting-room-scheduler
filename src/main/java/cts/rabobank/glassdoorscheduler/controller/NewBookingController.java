package cts.rabobank.glassdoorscheduler.controller;

import cts.rabobank.glassdoorscheduler.entity.BookingInfo;
import cts.rabobank.glassdoorscheduler.service.BookingService;
import cts.rabobank.glassdoorscheduler.service.UserInfoService;
import cts.rabobank.glassdoorscheduler.util.BookingValidator;
import cts.rabobank.glassdoorscheduler.util.CustomMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/newmeetingroom")
public class NewBookingController {

	@Autowired
	private BookingValidator bookingValidator;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping(value = "/book", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomMessage> bookRoom(@Valid @RequestBody BookingInfo bookingInfo, Errors errors) {

		bookingValidator.chkBookingRoomInputField(bookingInfo, errors);
		bookingService.bookRoom(bookingInfo);
		return new ResponseEntity<>(new CustomMessage(HttpStatus.OK.value(), "Meeting room booked successfully"),
				HttpStatus.OK);
	}
}
