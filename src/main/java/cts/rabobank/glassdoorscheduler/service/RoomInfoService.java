package cts.rabobank.glassdoorscheduler.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.rabobank.glassdoorscheduler.entity.Room;
import cts.rabobank.glassdoorscheduler.repo.RoomInfoRepo;

@Service
public class RoomInfoService {

	@Autowired
	private RoomInfoRepo roomInfoRepo;

	public List<Room> findAllRoom() {
		return roomInfoRepo.findAll();
	}

	public Room findByRoomId(Long id) {
		return roomInfoRepo.findById(id)
				.orElseThrow(()->new RuntimeException("Invalid Room ID"));
	}

}