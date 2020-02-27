package cts.rabobank.glassdoorscheduler.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cts.rabobank.glassdoorscheduler.entity.Booking;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
    public void deleteById(Long bookingId);
}