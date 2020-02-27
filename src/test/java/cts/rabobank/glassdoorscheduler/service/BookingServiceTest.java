package cts.rabobank.glassdoorscheduler.service;

import cts.rabobank.glassdoorscheduler.entity.*;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookingServiceTest {

    @Mock
    BookingInfo bookingInfo;

    @Mock
    BookingRepo bookingRepo;

    @Mock
    RoomInfoService roomInfoService;

    @Mock
    UserInfoService userInfoService;

    @Autowired
    @InjectMocks
    BookingService bookingService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        bookingInfo = new BookingInfo(1,1234,"Team",LocalDate.now(),LocalTime.now(),LocalTime.now());
    }
    
    private Room getRoomDetails(){
        return new Room(1L,"GD-ROOM-1");
       }

    private UserInfo getUserInfo(){
        return new UserInfo(2L,1234L,"teamname","passwordencrypted",true,new Date(),"admin","");
    }

    @Test
    @DisplayName("Add new meeting room schedule")
    public void testAddNewMeetingRoomSchedule(){
        doReturn(this.getRoomDetails()).when(roomInfoService).findByRoomId(anyLong());
        doReturn(this.getUserInfo()).when(userInfoService).findUserById(anyLong());
        when(bookingRepo.save(any())).thenReturn(new Booking());

        Room room = roomInfoService.findByRoomId((long) bookingInfo.getRoomId());

        Booking booking = new Booking();
        //BookingIdentity bookingIdentity = new BookingIdentity();
        booking.setBookingDate(bookingInfo.getBookingDate());
        booking.setBookingStartTime(bookingInfo.getBookingStartTime());
        booking.setBookingEndTime(bookingInfo.getBookingEndTime());

        UserInfo userInfo = userInfoService.findUserById((long) bookingInfo.getUsrEmpId());

        //Booking booking = new Booking();
        booking.setPurpose("");
        //booking.setBookingIdentity(bookingIdentity); 
        //booking.setUserInfo(userInfo);
        //booking.setRoomInfo(room); 

        //TODO chk the mock value here
        Booking savedBooking = bookingService.bookRoom(booking);
        Assertions.assertEquals(savedBooking.getClass(),booking.getClass());
    }

    @Test
    @DisplayName("Cancel meeting room schedule")
    @ExceptionHandler
    public void testCancelMeetingRoomSchedule() {
        bookingService.cancelMeetingRoom(1L);
    }

   
}