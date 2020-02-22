package cts.rabobank.glassdoorscheduler.service;

import cts.rabobank.glassdoorscheduler.entity.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cts.rabobank.glassdoorscheduler.repo.RoomInfoRepo;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RoomInfoServiceTest {
    @Autowired
    RoomInfoService roomInfoService;

    @Mock
    RoomInfoRepo roomInfoRepo;

    @Test
    @DisplayName("Test the count and room list details")
    void shouldFetchAllRoomDetails() {
        //TODO chk the mock and remove the lenient command
        lenient().doReturn(this.getRoomList()).when(roomInfoRepo).findAll();
        List<Room> rooms = roomInfoService.findAllRoom();
        Assertions.assertEquals(3,rooms.size(),"Should display the room fetch result correctly");
    }

    private List<Room> getRoomList(){
        return Arrays.asList(new Room(1L,"GD-ROOM-1"),
                new Room(2L,"GD-ROOM-2"),
                new Room(3L,"GD-ROOM-3")
        );
    }

}
