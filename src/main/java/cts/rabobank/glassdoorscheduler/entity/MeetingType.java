package cts.rabobank.glassdoorscheduler.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "BOOKING_PURPOSE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeetingType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PURPOSE_ID")
	private Long purposeId;
	
	@Column( name = "PURPOSE")
	private String purpose;

}
