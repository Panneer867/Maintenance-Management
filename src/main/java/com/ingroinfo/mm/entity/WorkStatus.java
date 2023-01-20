package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="MM_WORK_STATUS")
public class WorkStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long workStsId;
	private String workStatus;
}
