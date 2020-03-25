package cts.rabobank.glassdoorscheduler.service;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.MeetingDetail;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MeetingRoomDetailService {

    @Autowired
    private BookingRepo bookingrepo;

    private ArrayList<MeetingDetail> meetingDetails = new ArrayList<>();

    public List<MeetingDetail> getMeetingRoomDetails(){
        List<Booking> bookingDetail = bookingrepo.fetchBookingDetails();
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
        singleBookingDetail.setPurpose(booking.getPurpose());

       return singleBookingDetail;
    }
}
