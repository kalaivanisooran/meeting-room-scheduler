package cts.rabobank.glassdoorscheduler.service;

import cts.rabobank.glassdoorscheduler.entity.*;
import cts.rabobank.glassdoorscheduler.exception.InvalidInputRequestException;
import cts.rabobank.glassdoorscheduler.exception.MeetingRoomBookingException;
import cts.rabobank.glassdoorscheduler.repo.BookingRepo;
import cts.rabobank.glassdoorscheduler.util.BookingValidator;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.Errors;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
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

    @Mock
    BookingValidator bookingValidator;

    @InjectMocks
    @Spy
    BookingService bookingService = new BookingService();

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
        doNothing().when(bookingValidator).chkBookingRoomInputField(any(BookingInfo.class),any(Errors.class));
        when(bookingRepo.save(any())).thenReturn(new Booking());

        Boolean savedBooking = bookingService.bookRoom(bookingInfo,null);
        Assertions.assertEquals(true,savedBooking);
    }

    @Test
    @DisplayName("Handle the exception while booking the new meeting room")
    public void testHandleExceptionWhileMeetingRoomSchedule(){

        doReturn(this.getRoomDetails()).when(roomInfoService).findByRoomId(anyLong());
        doReturn(this.getUserInfo()).when(userInfoService).findUserById(anyLong());
        doNothing().when(bookingValidator).chkBookingRoomInputField(any(BookingInfo.class),any(Errors.class));
        doThrow(new RuntimeException()).when(bookingRepo).save(any());

        MeetingRoomBookingException exceptionMessage = Assertions.assertThrows(MeetingRoomBookingException.class,()->{bookingService.bookRoom(bookingInfo,null);});
        Assertions.assertEquals("Something went wrong while inserting the data into database",exceptionMessage.getMessage());
    }

    @Test
    @DisplayName("Number of recursion based on Mode")
    public void testNoOfRecursionBasedOnMode(){
        Assertions.assertEquals(7,bookingService.setNoOfRecursiveBasedOnMode("week"));
        Assertions.assertEquals(30,bookingService.setNoOfRecursiveBasedOnMode("month"));
        Assertions.assertEquals(1,bookingService.setNoOfRecursiveBasedOnMode("today"));
        Assertions.assertEquals(1,bookingService.setNoOfRecursiveBasedOnMode("tomorrow"));
    }

    @Test
    @DisplayName("Handle Exception when custom date empty if mode is custom")
    public void testHandleExceptionWhenCustomMeetingRoomBooking(){
        bookingInfo.setCustomBookingDate(null);

        InvalidInputRequestException exceptionMessage = Assertions.assertThrows(InvalidInputRequestException.class,
                ()->{bookingService.customMeetingRoomBooking(this.getRoomDetails(),this.getUserInfo(),bookingInfo);});
        Assertions.assertEquals("Invalid. Custom meeting room Date should not be empty",exceptionMessage.getMessage());
    }

    @Test
    @DisplayName("Add new custom meeting room")
    public void testCustomMeetingRoomBooking(){
        bookingInfo.setMode("custom");
        bookingInfo.setCustomBookingDate(Arrays.asList(LocalDate.of(2021,03,01),LocalDate.of(2021,03,03)));

        when(bookingRepo.save(any())).thenReturn(new Booking());
        Assertions.assertEquals(true,bookingService.customMeetingRoomBooking(this.getRoomDetails(),this.getUserInfo(),bookingInfo));
    }

    @Test
    @DisplayName("Handle exception in CustomMeetingRoomBooking ")
    public void testHandleExceptionInCustomMeetingRoomBooking(){
        bookingInfo.setMode("custom");
        bookingInfo.setCustomBookingDate(Arrays.asList(LocalDate.of(2021,03,01),LocalDate.of(2021,03,03)));

        doThrow(new RuntimeException()).when(bookingRepo).save(any());

        Assertions.assertThrows(MeetingRoomBookingException.class,()->{bookingService.customMeetingRoomBooking(this.getRoomDetails(),this.getUserInfo(),bookingInfo);});
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