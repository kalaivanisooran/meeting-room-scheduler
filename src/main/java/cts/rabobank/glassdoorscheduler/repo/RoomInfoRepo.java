package cts.rabobank.glassdoorscheduler.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cts.rabobank.glassdoorscheduler.entity.Room;

@Repository
public interface RoomInfoRepo extends JpaRepository<Room, Long> {

}
