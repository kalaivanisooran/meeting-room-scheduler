package cts.rabobank.glassdoorscheduler.service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.BookingInfo;
import cts.rabobank.glassdoorscheduler.entity.SearchResponse;
import cts.rabobank.glassdoorscheduler.entity.Searching;
import cts.rabobank.glassdoorscheduler.exception.InvalidInputRequestException;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;
import cts.rabobank.glassdoorscheduler.repo.SearchSpecifications;

@Service
@Transactional
public class BookingService {

	@Autowired
	private BookingRepo bookingrepo;


	public Booking bookRoom(Booking booking) {
		return bookingrepo.save(booking);
	}

	public void cancelMeetingRoom(Long bookingId) {
		if(!doBookingExists(bookingId)) {
			throw new InvalidInputRequestException("Invalid. Requested meeting room information is not available");
		}
		bookingrepo.deleteById(bookingId);
	}

	private boolean doBookingExists(Long bookingId) {
		Optional<Booking> booking = bookingrepo.findById(bookingId);
		if (booking.isPresent()) {
			return true;
		}
		return false;
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
			searchResp.setUserName(books.getUserInfo().getFullName());
			searchResp.setUsrEmpId(books.getUserInfo().getUsrEmpId());
			searchResp.setBookingId(books.getId());
			searchResp.setPurpose(books.getPurpose());

			searchRespList.add(searchResp);
		}

		return searchRespList;
	}

	public boolean canBookingAllowed(BookingInfo b) {
		boolean canBook = true;
		
		
		LocalTime bStrartTime  = b.getBookingStartTime().plus(1, ChronoUnit.MINUTES); 
		LocalTime bEndTime  = b.getBookingEndTime().minus(1, ChronoUnit.MINUTES); 
		
		Optional<Booking> booked = bookingrepo.doBookingSlotAvailable(b.getBookingDate(), b.getRoomId().longValue(),
				bStrartTime, bEndTime);
		if (booked.isPresent()) {
			canBook = false;
		}
		return canBook;
	}

}
