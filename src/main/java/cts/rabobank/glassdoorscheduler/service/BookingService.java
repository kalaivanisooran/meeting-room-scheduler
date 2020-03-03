package cts.rabobank.glassdoorscheduler.service;

import cts.rabobank.glassdoorscheduler.exception.InvalidInputRequestException;
import cts.rabobank.glassdoorscheduler.repo.BookingIDRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.SearchResponse;
import cts.rabobank.glassdoorscheduler.entity.BookingInfo;
import cts.rabobank.glassdoorscheduler.entity.Searching;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;
import cts.rabobank.glassdoorscheduler.repo.SearchSpecifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookingService {

	@Autowired
	private BookingRepo bookingrepo;

	@Autowired
	private BookingIDRepo bookingIDRepo;

	public Booking bookRoom(Booking booking) {
		return bookingrepo.save(booking);
	}

	public void cancelMeetingRoom(Long bookingId) {
		try {
			bookingrepo.deleteById(bookingId);
		} catch (Exception e) {
			throw new InvalidInputRequestException("Invalid. Requested meeting room information is not available");
		}
	}

	public List<SearchResponse> searchMeetingRooms(Searching searching) {

		List<SearchResponse> searchRespList = new ArrayList<SearchResponse>();
		
		List<Booking> bookingList = bookingrepo.findAll(SearchSpecifications.searchMeetingRooms(searching));

		for (Booking books : bookingList) {
			SearchResponse searchResp = new SearchResponse();
			searchResp.setBookingDate(books.getBookingDate());
			searchResp.setBookingStartTime(books.getBookingStartTime());
			searchResp.setBookingEndTime(books.getBookingEndTime());
			searchResp.setRoomId(books.getRoomInfo().getId());
			searchResp.setRoomName(books.getRoomInfo().getRoomName());
			searchResp.setUserId(books.getUserInfo().getId());
			searchResp.setUsername(books.getUserInfo().getUsername());
			searchResp.setUsrEmpId(books.getUserInfo().getUsrEmpId());

			searchRespList.add(searchResp);
		}

		return searchRespList;
	}
	
	
	public boolean canBookingAllowed(BookingInfo b) {
		boolean canBook = true;
		Optional<Booking> booked = bookingrepo.doBookingSlotAvailable(b.getBookingDate(), b.getRoomId().longValue(), b.getBookingStartTime(), b.getBookingEndTime());
		if(booked.isPresent()) {
			canBook = false;
		}
		return canBook;
	}

}
