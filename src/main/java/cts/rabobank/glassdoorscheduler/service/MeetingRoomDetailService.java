package cts.rabobank.glassdoorscheduler.service;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.BookingInfo;
import cts.rabobank.glassdoorscheduler.entity.MeetingDetail;
import cts.rabobank.glassdoorscheduler.entity.UserInfo;
import cts.rabobank.glassdoorscheduler.exception.InvalidInputRequestException;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;
import cts.rabobank.glassdoorscheduler.repo.SearchRepo;
import cts.rabobank.glassdoorscheduler.repo.SearchSpecifications;
import cts.rabobank.glassdoorscheduler.repo.UserInfoRepo;
import cts.rabobank.glassdoorscheduler.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;



@Service
@Transactional
public class MeetingRoomDetailService {

    @Autowired
    private BookingRepo bookingrepo;
    
    @Autowired
    private SearchRepo searchRepo;

    @Autowired
    private UserInfoRepo userInfoRepo;

    private ArrayList<MeetingDetail> meetingDetails = new ArrayList<>();

    public List<MeetingDetail> getAllMeetingRoomDetails(){
        List<Booking> bookingDetail = bookingrepo.fetchBookingDetails();
        if (!bookingDetail.isEmpty()) {
            meetingDetails = bookingDetail.stream()
                                            .map(this::convertToMeetingDetails)
                                            .collect(Collectors.toCollection(ArrayList::new));
        }
        return meetingDetails;
    }
    
    public List<MeetingDetail> getMeetingRoomDetails(BookingInfo bookingInfo) {

        UserInfo userInfo = Optional.ofNullable(userInfoRepo.findByUsrEmpId((long)bookingInfo.getUsrEmpId())).orElseThrow(()->new InvalidInputRequestException("Invalid. Requested user information is not available"));
        Long userId = userInfo.getId();


        int days = Util.setNoOfRecursiveBasedOnMode(bookingInfo.getBookingMode());
        LocalDate endDate = bookingInfo.getBookingStartDate().plusDays(days);

        List<Booking> bookingDetail = null;

        if (bookingInfo.getBookingMode().equals("custom")) {
             Specification<Booking> pred = SearchSpecifications.searchMeetingRooms(bookingInfo, endDate, userId);
                 bookingDetail = searchRepo.findAll(pred);

        } else {
                 Specification<Booking> pred = SearchSpecifications.searchMeetingRooms(bookingInfo, endDate, userId);
             bookingDetail = searchRepo.findAll(pred);
        }

        System.out.println("bookingDetail Specification "+bookingDetail.size());


        if (!bookingDetail.isEmpty()) {
            meetingDetails = bookingDetail.stream()
                                            .map(this::convertToMeetingDetails)
                                            .collect(Collectors.toCollection(ArrayList::new));
        }
        return meetingDetails;
    }


    protected MeetingDetail convertToMeetingDetails(Booking booking){

        MeetingDetail singleBookingDetail = new MeetingDetail();
        singleBookingDetail.setRoomName(booking.getRoomInfo().getRoomName());
        singleBookingDetail.setBookingStartDate(booking.getBookingStartDate());
        singleBookingDetail.setBookingStartTime(booking.getBookingStartTime());
        singleBookingDetail.setBookingEndTime(booking.getBookingEndTime());
        singleBookingDetail.setPurpose(booking.getMeetingType().getPurpose());

       return singleBookingDetail;
    }
}
