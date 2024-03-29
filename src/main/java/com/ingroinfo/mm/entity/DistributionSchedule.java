package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="MM_DISTRIBUTION_SCHEDULE")
public class DistributionSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long disScheduleId;
	private String division;
	private String subDivision;
	private String distLocation;
	private String distSchedule;
}
