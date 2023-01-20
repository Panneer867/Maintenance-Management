package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="MM_PRESSURE_TYPE")
public class PressureType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pressureId;
	private String pressureType;
}
