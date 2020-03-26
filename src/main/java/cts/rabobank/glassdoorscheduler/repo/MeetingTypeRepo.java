package cts.rabobank.glassdoorscheduler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cts.rabobank.glassdoorscheduler.entity.MeetingType;

public interface MeetingTypeRepo extends JpaRepository<MeetingType, Long>{

	
}
