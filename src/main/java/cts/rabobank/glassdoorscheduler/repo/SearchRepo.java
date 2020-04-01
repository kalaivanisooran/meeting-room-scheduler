package cts.rabobank.glassdoorscheduler.repo;

import cts.rabobank.glassdoorscheduler.entity.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepo extends JpaRepository<Booking, Long>, JpaSpecificationExecutor {

}
