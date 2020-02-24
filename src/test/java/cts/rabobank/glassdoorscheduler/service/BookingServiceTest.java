package cts.rabobank.glassdoorscheduler.service;

import cts.rabobank.glassdoorscheduler.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Time;
import java.util.Date;

@SpringBootTest
public class BookingServiceTest {

    @Autowired
    BookingInfo bookingInfo;

    @Autowired
    BookingService bookingService;

    @Autowired
    RoomInfoService roomInfoService;

    @Autowired
    UserInfoService userInfoService;

    @BeforeEach
    public void init(){
        bookingInfo = new BookingInfo(1,1234,"Team",new Date(), Time.valueOf("11:00:00"),Time.valueOf("12:00:00"));
    }

    @Test
    @DisplayName("Add new meeting room schedule")
    public void testAddNewMeetingRoomSchedule(){

        Room room = roomInfoService.findByRoomId((long) bookingInfo.getRoomId());

        BookingIdentity bookingIdentity = new BookingIdentity();
        bookingIdentity.setBookingDate(bookingInfo.getBookingDate());
        bookingIdentity.setBookingStartTime(bookingInfo.getBookingStartTime());
        bookingIdentity.setBookingEndTime(bookingInfo.getBookingEndTime());
        bookingIdentity.setRoomInfo(room);

        UserInfo userInfo = userInfoService.findUserById((long) bookingInfo.getUsrEmpId());

        Booking booking = new Booking();
        booking.setPurpose("");
        booking.setBookingIdentity(bookingIdentity);
        booking.setUserInfo(userInfo);

        Booking savedBooking = bookingService.bookRoom(booking);
        Assertions.assertEquals(savedBooking.getClass(),booking.getClass());
        Assertions.assertEquals(savedBooking.getBookingIdentity(),booking.getBookingIdentity());

    }

}