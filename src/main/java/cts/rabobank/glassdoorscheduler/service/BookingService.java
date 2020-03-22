package cts.rabobank.glassdoorscheduler.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.BookingInfo;
import cts.rabobank.glassdoorscheduler.entity.BookingPurpose;
import cts.rabobank.glassdoorscheduler.entity.Room;
import cts.rabobank.glassdoorscheduler.entity.SearchResponse;
import cts.rabobank.glassdoorscheduler.entity.Searching;
import cts.rabobank.glassdoorscheduler.entity.UserInfo;
import cts.rabobank.glassdoorscheduler.exception.InvalidInputRequestException;
import cts.rabobank.glassdoorscheduler.repo.BookingPurposeRepo;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;

@Service
@Transactional
public class BookingService {

	@Autowired
	RoomInfoService roomInfoService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private BookingRepo bookingrepo;
	
	@Autowired
	private BookingPurposeRepo bookingPurposeRepo;


	public Booking bookRoom(BookingInfo bookingInfo) {

		Room room = roomInfoService.findByRoomId((long) bookingInfo.getRoomId());
		UserInfo userInfo = userInfoService.findUserById((long) bookingInfo.getUsrEmpId());

		Booking booking = new Booking();
		booking.setBookingStartDate(bookingInfo.getBookingStartDate());

		String mode = "tomorrow";

		if(mode=="week"){
			booking.setBookingEndDate(bookingInfo.getBookingStartDate().plusWeeks(1));
		} else if (mode=="tomorrow"){
			booking.setBookingStartDate(bookingInfo.getBookingStartDate().plusDays(1));
			booking.setBookingEndDate(bookingInfo.getBookingStartDate().plusDays(1));
		} else if ( mode=="month"){
			booking.setBookingEndDate(bookingInfo.getBookingStartDate().plusMonths(1));
		} else if ( mode=="today"){
			booking.setBookingStartDate(bookingInfo.getBookingStartDate());
			booking.setBookingEndDate(bookingInfo.getBookingEndDate());
		}

		booking.setBookingStartTime(bookingInfo.getBookingStartTime());
		booking.setBookingEndTime(bookingInfo.getBookingEndTime());
		booking.setRoomInfo(room);
		booking.setPurpose(bookingInfo.getPurpose());
		booking.setUserInfo(userInfo);
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
//
//		List<Booking> bookingList = bookingrepo.findAllByOrderByIdDesc(SearchSpecifications.searchMeetingRooms(searching));
//
//		for (Booking books : bookingList) {
//			SearchResponse searchResp = new SearchResponse();
//			//TODO check and change the search condition
//			//searchResp.setBookingDate(books.getBookingDate());
//			searchResp.setBookingStartTime(books.getBookingStartTime());
//			searchResp.setBookingEndTime(books.getBookingEndTime());
//			searchResp.setRoomId(books.getRoomInfo().getId());
//			searchResp.setRoomName(books.getRoomInfo().getRoomName());
//			searchResp.setUserId(books.getUserInfo().getId());
//			searchResp.setUserName(books.getUserInfo().getFullName());
//			searchResp.setUsrEmpId(books.getUserInfo().getUsrEmpId());
//			searchResp.setBookingId(books.getId());
//			searchResp.setPurpose(books.getPurpose());
//
//			searchRespList.add(searchResp);
//		}

		return searchRespList;
	}

//	public boolean canBookingAllowed(BookingInfo b) {
//		boolean canBook = true;
//
//
//		LocalTime bStrartTime  = b.getBookingStartTime().plus(1, ChronoUnit.MINUTES);
//		LocalTime bEndTime  = b.getBookingEndTime().minus(1, ChronoUnit.MINUTES);
//
//		Optional<Booking> booked = bookingrepo.doBookingSlotAvailable(b.getBookingStartDate(), b.getRoomId().longValue(),
//				bStrartTime, bEndTime);
//		if (booked.isPresent()) {
//			canBook = false;
//		}
//		return canBook;
//	}
	
	public List<BookingPurpose> fetchAllBookingPurposes() {
		return bookingPurposeRepo.findAll();
	}

}
