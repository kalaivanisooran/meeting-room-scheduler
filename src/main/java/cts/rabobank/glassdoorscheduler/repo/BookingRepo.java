package cts.rabobank.glassdoorscheduler.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.BookingId;

@Repository
public interface BookingRepo extends JpaRepository<Booking, BookingId>{

}
