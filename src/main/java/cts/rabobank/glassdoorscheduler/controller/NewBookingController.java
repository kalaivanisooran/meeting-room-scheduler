package cts.rabobank.glassdoorscheduler.controller;

import java.util.List;

import javax.validation.Valid;

import cts.rabobank.glassdoorscheduler.entity.MeetingDetail;
import cts.rabobank.glassdoorscheduler.service.MeetingRoomDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cts.rabobank.glassdoorscheduler.entity.BookingInfo;
import cts.rabobank.glassdoorscheduler.entity.BookingPurpose;
import cts.rabobank.glassdoorscheduler.service.BookingService;
import cts.rabobank.glassdoorscheduler.util.BookingValidator;
import cts.rabobank.glassdoorscheduler.util.CustomMessage;

@RestController
@RequestMapping("/newmeetingroom")
public class NewBookingController {

	@Autowired
	private BookingValidator bookingValidator;

    @Autowired
    private BookingService bookingService;

    @Autowired
	private MeetingRoomDetailService meetingRoomDetailService;
    
    @PostMapping(value = "/book", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomMessage> bookRoom(@Valid @RequestBody BookingInfo bookingInfo, Errors errors) {

		bookingValidator.chkBookingRoomInputField(bookingInfo, errors);
		bookingService.bookRoom(bookingInfo);
		return new ResponseEntity<>(new CustomMessage(HttpStatus.OK.value(), "Meeting room booked successfully"),
				HttpStatus.OK);
	}
    
    @GetMapping(value = "/fetchBookingPurposes")
	public ResponseEntity<?> listAllBookingPurposes() {
		
		List<BookingPurpose> bookingPurposeList = bookingService.fetchAllBookingPurposes();
		if (bookingPurposeList.isEmpty()) {
			return new ResponseEntity<CustomMessage>(new CustomMessage(HttpStatus.OK.value(), "No Purpose found"), HttpStatus.OK);
		}
		return new ResponseEntity<List<BookingPurpose>>(bookingPurposeList, HttpStatus.OK);
	}

	@GetMapping(value="/getmeetingdetails", produces = "application/json")
	public ResponseEntity<?> getAllMeetingRoomDetails() {

		List<MeetingDetail> meetingDetailRecord = meetingRoomDetailService.getMeetingRoomDetails();
		if (meetingDetailRecord.isEmpty()) {
			return new ResponseEntity<CustomMessage>(new CustomMessage(HttpStatus.OK.value(), "Meeting Room Details are not available"), HttpStatus.OK);
		}
		return new ResponseEntity<List<MeetingDetail>>(meetingDetailRecord, HttpStatus.OK);
	}
}
