package cts.rabobank.glassdoorscheduler.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class BookingId implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOKING_ID", nullable = false)
    private Long bookingId;
}