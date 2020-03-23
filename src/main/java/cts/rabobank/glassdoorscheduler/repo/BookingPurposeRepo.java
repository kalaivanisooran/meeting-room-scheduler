package cts.rabobank.glassdoorscheduler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cts.rabobank.glassdoorscheduler.entity.BookingPurpose;

public interface BookingPurposeRepo extends JpaRepository<BookingPurpose, Long>{

	
}
