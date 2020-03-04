package cts.rabobank.glassdoorscheduler.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cts.rabobank.glassdoorscheduler.entity.Room;
import cts.rabobank.glassdoorscheduler.service.RoomInfoService;
import cts.rabobank.glassdoorscheduler.util.CustomMessage;

@RestController
@RequestMapping("/room")
public class RoomInfoController {

	@Autowired
	private RoomInfoService roomInfoService;

	
	@GetMapping(value = "/fetchrooms")
	public ResponseEntity<?> listAllRooms() {
		
		List<Room> rooms = roomInfoService.findAllRoom();
		if (rooms.isEmpty()) {
			return new ResponseEntity<CustomMessage>(new CustomMessage(HttpStatus.OK.value(), "No Rooms found"), HttpStatus.OK);
		}
		return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
}