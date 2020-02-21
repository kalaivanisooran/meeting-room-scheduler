package cts.rabobank.glassdoorscheduler.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cts.rabobank.glassdoorscheduler.entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

	
}
