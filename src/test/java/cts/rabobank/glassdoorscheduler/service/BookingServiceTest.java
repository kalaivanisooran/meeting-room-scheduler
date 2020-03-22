package cts.rabobank.glassdoorscheduler.service;

import cts.rabobank.glassdoorscheduler.entity.*;
import cts.rabobank.glassdoorscheduler.exception.InvalidInputRequestException;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
        bookingInfo = new BookingInfo();
        bookingInfo.setRoomId(1);
        bookingInfo.setUsrEmpId(1234);
        bookingInfo.setBookingStartDate(LocalDate.now());
        bookingInfo.setBookingStartTime(LocalTime.now());
        bookingInfo.setBookingEndTime(LocalTime.now().plusHours(1));
        bookingInfo.setPurpose("reason");
        bookingInfo.setMode("today");
    }


    @Test
    @DisplayName("Add new meeting room schedule")
    public void testAddNewMeetingRoomSchedule(){
        doReturn(this.getRoomDetails()).when(roomInfoService).findByRoomId(anyLong());
        doReturn(this.getUserInfo()).when(userInfoService).findUserById(anyLong());
        when(bookingRepo.save(any())).thenReturn(new Booking());
        //TODO check inner method mock
        //doReturn(true).when(Mockito.spy(bookingService)).recordMeetingRoomBasedOnMode(any(),any(),any(),any());

        Room room = roomInfoService.findByRoomId((long) bookingInfo.getRoomId());
        UserInfo userInfo = userInfoService.findUserById((long) bookingInfo.getUsrEmpId());
       // Boolean savedBooking = bookingService.bookRoom(bookingInfo,null);
        //TODO check book savings
        //Assertions.assertEquals(true,savedBooking);
    }

    @Test
    @DisplayName("Cancel meeting room schedule")
    public void testCancelMeetingRoomSchedule() {
       // bookingService.cancelMeetingRoom(1L);
        //TODO check scenario
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("Catch exception while Cancel meeting room schedule")
    public void testCancelMeetingRoomWhichIsNotListed() {
        doThrow(new InvalidInputRequestException("Invalid. Requested meeting room information is not available")).when(bookingRepo).deleteById(any());
        Assertions.assertThrows(InvalidInputRequestException.class,()->bookingService.cancelMeetingRoom(1L));
    }

    private Room getRoomDetails(){
        return new Room(1L,"GD-ROOM-1");
    }

    private UserInfo getUserInfo(){
        return new UserInfo(2L,1234L,"teamname","passwordencrypted",true,new Date(),"admin","");
    }
}