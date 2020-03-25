package cts.rabobank.glassdoorscheduler.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import cts.rabobank.glassdoorscheduler.exception.MeetingRoomBookingException;
import cts.rabobank.glassdoorscheduler.util.BookingValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	@Autowired
	private BookingValidator bookingValidator;

	private final Logger logger = LoggerFactory.getLogger(BookingService.class);

	public Boolean bookRoom(BookingInfo bookingInfo) {

		Room room = roomInfoService.findByRoomId((long) bookingInfo.getRoomId());
		UserInfo userInfo = userInfoService.findUserById((long) bookingInfo.getUsrEmpId());
		if(bookingInfo.getMode().equals("custom")){
			return this.customMeetingRoomBooking(room,userInfo,bookingInfo);
		}else{
			return this.recordMeetingRoomBasedOnMode(room,userInfo,bookingInfo,this.setNoOfRecursiveBasedOnMode(bookingInfo.getMode()));
		}
	}


	protected Boolean recordMeetingRoomBasedOnMode(Room room, UserInfo userInfo,BookingInfo bookingInfo,int noOfRecurrsive) {

		assert bookingInfo != null;
		LocalDate currentBookingDate = bookingInfo.getBookingStartDate();

		for (int i=0;i<noOfRecurrsive;i++){
			currentBookingDate = (i == 0)?currentBookingDate: currentBookingDate.plusDays(1);
			this.recordInDB(room,userInfo,bookingInfo,currentBookingDate);
		}
		return true;
	}


	protected Boolean customMeetingRoomBooking(Room room,UserInfo userInfo,BookingInfo bookingInfo){

		if(bookingInfo.getCustomBookingDate() == null) {
			throw new InvalidInputRequestException("Invalid. Custom meeting room Date should not be empty");
		}

		bookingInfo.getCustomBookingDate().forEach(customBookingDate->{
			this.recordInDB(room,userInfo,bookingInfo,customBookingDate);
		});

		return true;
	}

	private void recordInDB(Room room,UserInfo userInfo,BookingInfo bookingInfo,LocalDate bookingDate){
		try {
			Booking booking = new Booking();
			booking.setBookingStartDate(bookingDate);
			booking.setBookingStartTime(bookingInfo.getBookingStartTime());
			booking.setBookingEndTime(bookingInfo.getBookingEndTime());
			booking.setRoomInfo(room);
			booking.setPurpose(bookingInfo.getPurpose());
			booking.setUserInfo(userInfo);
			bookingrepo.save(booking);
		} catch (Exception e) {
			//ConstraintViolationException
			//TODO check and catch the exception type
			logger.error("Something went wrong while inserting the data into database");
			throw new MeetingRoomBookingException("Something went wrong while inserting the data into database");
		}
	}


	protected int setNoOfRecursiveBasedOnMode(String mode){
		int noOfRecurrsive;

		switch (mode) {
			case "week":
				noOfRecurrsive = 7;
				break;
			case "month":
				noOfRecurrsive = 30;
				break;
			default:
				noOfRecurrsive = 1;
				break;
		}
		return noOfRecurrsive;
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
