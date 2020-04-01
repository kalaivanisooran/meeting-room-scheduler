package cts.rabobank.glassdoorscheduler.service;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.MeetingDetail;
import cts.rabobank.glassdoorscheduler.entity.MeetingType;
import cts.rabobank.glassdoorscheduler.entity.Room;
import cts.rabobank.glassdoorscheduler.entity.UserInfo;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class MeetingRoomDetailServiceTest {

    @Mock
    BookingRepo bookingRepo;


    @InjectMocks
    @Spy
    MeetingRoomDetailService meetingRoomDetailService = new MeetingRoomDetailService();

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Get meeting Details")
    public void testGetMeetingRoomDetails(){

        when(bookingRepo.fetchBookingDetails()).thenReturn(this.getBookingSampleData());

        List<MeetingDetail> meetingDetails = meetingRoomDetailService.getAllMeetingRoomDetails();
        Assertions.assertEquals(2,meetingDetails.size());
        Assertions.assertEquals(LocalDate.now(),meetingDetails.get(0).getBookingStartDate());
    }

    @Test
    @DisplayName("When Booking Detail is Empty")
    public void testWhenMeetingRoomDetailIsEmpty(){
        when(bookingRepo.findAll()).thenReturn(new ArrayList<>());

        List<MeetingDetail> meetingDetails = meetingRoomDetailService.getAllMeetingRoomDetails();
        Assertions.assertTrue(meetingDetails.isEmpty());
        Assertions.assertEquals(0,meetingDetails.size());
    }

    @Test
    @DisplayName("Get meeting description details from Booking entity")
    public void testconvertToMeetingDetails(){
        MeetingDetail meetingDetail = meetingRoomDetailService.convertToMeetingDetails(this.getBookingSampleData().get(0));
        Assertions.assertEquals("room1", meetingDetail.getRoomName());
        Assertions.assertEquals("Stand-up", meetingDetail.getPurpose());
        Assertions.assertEquals(LocalDate.now(), meetingDetail.getBookingStartDate());
        Assertions.assertEquals(LocalTime.of(11,30), meetingDetail.getBookingStartTime());
        Assertions.assertEquals(LocalTime.of(12,30), meetingDetail.getBookingEndTime());
    }

    private List<Booking> getBookingSampleData(){
        Booking booking1 =new Booking();
        booking1.setRoomInfo(new Room(3L,"room1"));
        booking1.setUserInfo(new UserInfo());
        booking1.setMeetingType(new MeetingType(1L, "Stand-up"));
        booking1.setBookingStartDate(LocalDate.now());
        booking1.setBookingStartTime(LocalTime.of(11,30));
        booking1.setBookingEndTime(LocalTime.of(12,30));

        Booking booking2 =new Booking();
        booking2.setRoomInfo(new Room(3L,"room2"));
        booking2.setUserInfo(new UserInfo());
       
        booking2.setMeetingType(new MeetingType(1L, "Retro"));
        booking2.setBookingStartDate(LocalDate.now().plusDays(3));
        booking2.setBookingStartTime(LocalTime.of(10,30));
        booking2.setBookingEndTime(LocalTime.of(11,30));

        return Arrays.asList(booking1,booking2);
    }

}
