package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="MM_DISTRIBUTION_LOCATION")
public class DistributionLocation {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long disLocId;
	private String division;
	private String subDivision;
	private String distlocation;
}
