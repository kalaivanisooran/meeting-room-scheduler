package cts.rabobank.glassdoorscheduler.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.rabobank.glassdoorscheduler.entity.Room;
import cts.rabobank.glassdoorscheduler.repo.RoomInfoRepo;

@Service
public class RoomInfoService {

	@Autowired
	private RoomInfoRepo roomInfoRepo;

	Logger logger = LoggerFactory.getLogger(RoomInfoService.class);

	public List<Room> findAllRoom() {
		return roomInfoRepo.findAll();
	}

}
