package cts.rabobank.glassdoorscheduler.service;

import cts.rabobank.glassdoorscheduler.entity.Room;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import cts.rabobank.glassdoorscheduler.repo.RoomInfoRepo;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class RoomInfoServiceTest {

    @Mock
    RoomInfoRepo roomInfoRepo;

    @Autowired
    @InjectMocks
    RoomInfoService roomInfoService;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test room list details and it count")
    void shouldFetchAllRoomDetails() {
        doReturn(this.getRoomList()).when(roomInfoRepo).findAll();
        List<Room> rooms = this.roomInfoService.findAllRoom();
        Assertions.assertEquals(2,rooms.size(),"Should display the room fetch result correctly");
    }

    @Test
    @DisplayName("Test fetch the room details by room id")
    void shouldReturnRoomDetailsByID(){
        Room stubData = this.getRoomList().stream().findFirst().orElse(new Room());
        doReturn((this.getRoomList().stream().findFirst())).when(roomInfoRepo).findById(anyLong());
        Room room = this.roomInfoService.findByRoomId(2L);
        Assertions.assertEquals(stubData.getRoomName(),room.getRoomName());
    }

    @Test
    @DisplayName("Test to handle the exception")
    void shouldHandleInvalidRoomIDRequest(){
        doThrow(new RuntimeException("Invalid Room ID")).when(roomInfoRepo).findById(anyLong());
        Exception exception = Assertions.assertThrows(RuntimeException.class,()->this.roomInfoService.findByRoomId(2L),"Should throw runtime exception");
        Assertions.assertEquals("Invalid Room ID",exception.getMessage(),"Should throw the exact error message");
    }

    private List<Room> getRoomList(){
        return Arrays.asList(new Room(1L,"GD-ROOM-1"),
                new Room(2L,"GD-ROOM-2")
               // new Room(3L,"GD-ROOM-3")
        );
    }

}
