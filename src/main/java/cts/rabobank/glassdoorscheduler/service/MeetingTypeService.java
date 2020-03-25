package cts.rabobank.glassdoorscheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.rabobank.glassdoorscheduler.entity.MeetingType;
import cts.rabobank.glassdoorscheduler.exception.InvalidInputRequestException;
import cts.rabobank.glassdoorscheduler.repo.MeetingTypeRepo;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class MeetingTypeService {

        @Autowired
        private MeetingTypeRepo meetingTypeRepo;


        public List<MeetingType> findAllMeetingType() {
                return meetingTypeRepo.findAll();
        }

        public MeetingType findByMeetingTypeId(Long id) {
                return meetingTypeRepo.findById(id)
                                .orElseThrow(()->new InvalidInputRequestException("Invalid. Requested meetingtype information is not available"));
        }
}
